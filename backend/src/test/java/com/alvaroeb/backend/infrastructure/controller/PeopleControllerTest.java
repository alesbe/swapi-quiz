package com.alvaroeb.backend.infrastructure.controller;

import com.alvaroeb.backend.domain.model.Person;
import com.alvaroeb.backend.domain.port.PeopleService;
import com.alvaroeb.backend.infrastructure.controller.dto.QueryParams;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.Collections;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class PeopleControllerTest {

    private PeopleService peopleService;
    private PeopleController peopleController;

    @BeforeEach
    void setUp() {
        peopleService = mock(PeopleService.class);
        peopleController = new PeopleController(peopleService);
    }

    @Test
    void getPeople_shouldDelegateToServiceWithCorrectParams() {
        // Arrange
        QueryParams queryParams = new QueryParams();
        queryParams.setPage(2);
        queryParams.setSize(5);
        queryParams.setSearch("Luke");
        queryParams.setSort("name");
        queryParams.setDirection("desc");

        Page<Person> expectedPage = new PageImpl<>(Collections.emptyList());
        when(peopleService.getPeople(2, 5, "Luke", "name", "desc")).thenReturn(expectedPage);

        // Act
        Page<Person> result = peopleController.getPeople(queryParams);

        // Assert
        Assertions.assertThat(result).isSameAs(expectedPage);
        verify(peopleService, times(1)).getPeople(2, 5, "Luke", "name", "desc");
    }

    @Test
    void getPeople_shouldHandleDefaultQueryParams() {
        // Arrange
        QueryParams queryParams = new QueryParams(); // default values

        Page<Person> expectedPage = new PageImpl<>(Collections.emptyList());
        when(peopleService.getPeople(0, 15, "", "name", "asc")).thenReturn(expectedPage);

        // Act
        Page<Person> result = peopleController.getPeople(queryParams);

        // Assert
        assertThat(result).isSameAs(expectedPage);
        verify(peopleService, times(1)).getPeople(0, 15, "", "name", "asc");
    }
}