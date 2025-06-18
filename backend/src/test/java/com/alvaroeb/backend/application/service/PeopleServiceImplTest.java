package com.alvaroeb.backend.application.service;

import com.alvaroeb.backend.domain.model.Person;
import com.alvaroeb.backend.infrastructure.mapper.PersonMapper;
import com.alvaroeb.backend.infrastructure.swapi.client.SwapiClient;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPersonDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PeopleServiceImplTest {

    private SwapiClient swapiClient;
    private PersonMapper personMapper;
    private PeopleServiceImpl peopleService;

    @BeforeEach
    void setUp() {
        swapiClient = mock(SwapiClient.class);
        personMapper = mock(PersonMapper.class);
        peopleService = new PeopleServiceImpl(swapiClient, personMapper);
    }

    @Test
    void getPeople_returnsPagedPeople_sortedAndFiltered() {
        SwapiPersonDTO dto1 = new SwapiPersonDTO();
        dto1.setName("Luke Skywalker");
        SwapiPersonDTO dto2 = new SwapiPersonDTO();
        dto2.setName("Leia Organa");
        SwapiPersonDTO dto3 = new SwapiPersonDTO();
        dto3.setName("Han Solo");

        Person person1 = new Person();
        person1.setName("Luke Skywalker");
        Person person2 = new Person();
        person2.setName("Leia Organa");
        Person person3 = new Person();
        person3.setName("Han Solo");

        when(swapiClient.getAllPeople()).thenReturn(List.of(dto1, dto2, dto3));
        when(personMapper.toPerson(dto1)).thenReturn(person1);
        when(personMapper.toPerson(dto2)).thenReturn(person2);
        when(personMapper.toPerson(dto3)).thenReturn(person3);

        // Search for "l", sort by name desc, page 0, size 2
        Page<Person> result = peopleService.getPeople(0, 2, "l", "name", "DESC");

        assertThat(result.getContent()).extracting(Person::getName)
                .containsExactly("Luke Skywalker", "Leia Organa");
        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getSize()).isEqualTo(2);
        assertThat(result.getSort().getOrderFor("name").getDirection()).isEqualTo(Sort.Direction.DESC);
    }

    @Test
    void getPeople_returnsEmptyPage_whenNoPeople() {
        when(swapiClient.getAllPeople()).thenReturn(List.of());

        Page<Person> result = peopleService.getPeople(0, 5, null, null, null);

        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isZero();
    }

    @Test
    void getPeople_appliesDefaultSortAndDirection() {
        SwapiPersonDTO dto1 = new SwapiPersonDTO();
        dto1.setName("Boba Fett");
        SwapiPersonDTO dto2 = new SwapiPersonDTO();
        dto2.setName("Anakin Skywalker");

        Person person1 = new Person();
        person1.setName("Boba Fett");
        Person person2 = new Person();
        person2.setName("Anakin Skywalker");

        when(swapiClient.getAllPeople()).thenReturn(List.of(dto1, dto2));
        when(personMapper.toPerson(dto1)).thenReturn(person1);
        when(personMapper.toPerson(dto2)).thenReturn(person2);

        // No sort or direction provided, should default to name ASC
        Page<Person> result = peopleService.getPeople(0, 10, null, null, null);

        assertThat(result.getContent()).extracting(Person::getName)
                .containsExactly("Anakin Skywalker", "Boba Fett");
        assertThat(result.getSort().getOrderFor("name").getDirection()).isEqualTo(Sort.Direction.ASC);
    }

    @Test
    void getPeople_returnsEmptyPage_whenPageOutOfBounds() {
        SwapiPersonDTO dto1 = new SwapiPersonDTO();
        dto1.setName("Yoda");
        Person person1 = new Person();
        person1.setName("Yoda");

        when(swapiClient.getAllPeople()).thenReturn(List.of(dto1));
        when(personMapper.toPerson(dto1)).thenReturn(person1);

        // Only 1 element, page 1 with size 1 should be empty
        Page<Person> result = peopleService.getPeople(1, 1, null, null, null);

        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isEqualTo(1);
    }

    @Test
    void getPeople_filtersBySearchCaseInsensitive() {
        SwapiPersonDTO dto1 = new SwapiPersonDTO();
        dto1.setName("Obi-Wan Kenobi");
        SwapiPersonDTO dto2 = new SwapiPersonDTO();
        dto2.setName("Padmé Amidala");

        Person person1 = new Person();
        person1.setName("Obi-Wan Kenobi");
        Person person2 = new Person();
        person2.setName("Padmé Amidala");

        when(swapiClient.getAllPeople()).thenReturn(List.of(dto1, dto2));
        when(personMapper.toPerson(dto1)).thenReturn(person1);
        when(personMapper.toPerson(dto2)).thenReturn(person2);

        Page<Person> result = peopleService.getPeople(0, 10, "obi", null, null);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Obi-Wan Kenobi");
    }
}