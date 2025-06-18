package com.alvaroeb.backend.infrastructure.controller;

import com.alvaroeb.backend.domain.model.Planet;
import com.alvaroeb.backend.domain.port.PlanetsService;
import com.alvaroeb.backend.infrastructure.controller.dto.QueryParams;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class PlanetsControllerTest {

    private PlanetsService planetsService;
    private PlanetsController planetsController;

    @BeforeEach
    void setUp() {
        planetsService = mock(PlanetsService.class);
        planetsController = new PlanetsController(planetsService);
    }

    @Test
    void getPlanets_shouldDelegateToServiceWithCorrectParams() {
        // Arrange
        QueryParams queryParams = new QueryParams();
        queryParams.setPage(1);
        queryParams.setSize(10);
        queryParams.setSearch("Tatooine");
        queryParams.setSort("name");
        queryParams.setDirection("asc");

        Page<Planet> expectedPage = new PageImpl<>(Collections.emptyList());
        when(planetsService.getPlanets(1, 10, "Tatooine", "name", "asc")).thenReturn(expectedPage);

        // Act
        Page<Planet> result = planetsController.getPlanets(queryParams);

        // Assert
        Assertions.assertThat(result).isSameAs(expectedPage);

        verify(planetsService, times(1)).getPlanets(1, 10, "Tatooine", "name", "asc");
    }

    @Test
    void getPlanets_shouldHandleDefaultQueryParams() {
        // Arrange
        QueryParams queryParams = new QueryParams(); // default values

        Page<Planet> expectedPage = new PageImpl<>(Collections.emptyList());

        when(planetsService.getPlanets(0, 15, "", "name", "asc")).thenReturn(expectedPage);

        // Act
        Page<Planet> result = planetsController.getPlanets(queryParams);

        // Assert
        assertThat(result).isSameAs(expectedPage);
        verify(planetsService, times(1)).getPlanets(0, 15, "", "name", "asc");
    }

}