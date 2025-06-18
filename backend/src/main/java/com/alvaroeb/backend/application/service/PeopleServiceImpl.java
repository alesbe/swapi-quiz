package com.alvaroeb.backend.application.service;

import com.alvaroeb.backend.application.util.PersonComparators;
import com.alvaroeb.backend.domain.model.Person;
import com.alvaroeb.backend.domain.port.PeopleService;
import com.alvaroeb.backend.infrastructure.mapper.PersonMapper;
import com.alvaroeb.backend.infrastructure.swapi.client.SwapiClient;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPersonDTO;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final SwapiClient swapiClient;
    private final PersonMapper personMapper;

    public PeopleServiceImpl(SwapiClient swapiClient, PersonMapper personMapper) {
        this.swapiClient = swapiClient;
        this.personMapper = personMapper;
    }

    @Override
    public Page<Person> getPeople(Integer page, Integer size, String search, String sort, String direction) {
        List<SwapiPersonDTO> allDtos = swapiClient.getAllPeople();
        List<Person> allPeople = allDtos.stream()
                .map(personMapper::toPerson)
                .collect(Collectors.toList());

        if (search != null && !search.isBlank()) {
            String query = search.toLowerCase(Locale.ROOT);
            allPeople = allPeople.stream()
                    .filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(query))
                    .collect(Collectors.toList());
        }

        Sort.Direction sortDir = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.ASC);
        String sortField = (sort != null && !sort.isBlank()) ? sort : "name";

        allPeople = allPeople.stream()
                .sorted(PersonComparators.getComparator(sortField, sortDir))
                .collect(Collectors.toList());

        int start = page * size;
        int end = Math.min(start + size, allPeople.size());
        List<Person> pageContent = start >= allPeople.size() ? List.of() : allPeople.subList(start, end);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDir, sortField));
        return new PageImpl<>(pageContent, pageRequest, allPeople.size());
    }
}
