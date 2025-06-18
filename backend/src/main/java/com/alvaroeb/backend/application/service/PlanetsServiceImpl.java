package com.alvaroeb.backend.application.service;

import com.alvaroeb.backend.application.util.PlanetComparators;
import com.alvaroeb.backend.domain.model.Planet;
import com.alvaroeb.backend.domain.port.PlanetsService;
import com.alvaroeb.backend.infrastructure.mapper.PlanetMapper;
import com.alvaroeb.backend.infrastructure.swapi.client.SwapiClient;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPlanetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.alvaroeb.backend.domain.util.PlanetUtils.isUnknown;

@Service
public class PlanetsServiceImpl implements PlanetsService {

    private final SwapiClient swapiClient;
    private final PlanetMapper planetMapper;

    public PlanetsServiceImpl(SwapiClient swapiClient, PlanetMapper planetMapper) {
        this.swapiClient = swapiClient;
        this.planetMapper = planetMapper;
    }

    @Override
    public Page<Planet> getPlanets(Integer page, Integer size, String search, String sort, String direction) {
        List<SwapiPlanetDTO> allDtos = swapiClient.getAllPlanets();

        List<Planet> allPlanets = allDtos.stream()
                .map(planetMapper::toPlanet)
                .filter(p -> !isUnknown(p)) // eliminar los planetas "vacíos"
                .collect(Collectors.toList());

        if (search != null && !search.isBlank()) {
            String query = search.toLowerCase(Locale.ROOT);
            allPlanets = allPlanets.stream()
                    .filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(query))
                    .collect(Collectors.toList());
        }

        Sort.Direction sortDir = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.ASC);
        String sortField = (sort != null && !sort.isBlank()) ? sort : "name";

        allPlanets = allPlanets.stream()
                .sorted(PlanetComparators.getComparator(sortField, sortDir))
                .collect(Collectors.toList());

        int start = page * size;
        int end = Math.min(start + size, allPlanets.size());
        List<Planet> pageContent = start >= allPlanets.size() ? List.of() : allPlanets.subList(start, end);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDir, sortField));
        return new PageImpl<>(pageContent, pageRequest, allPlanets.size());
    }
}
