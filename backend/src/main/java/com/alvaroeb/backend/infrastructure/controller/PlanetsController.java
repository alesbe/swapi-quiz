package com.alvaroeb.backend.infrastructure.controller;

import com.alvaroeb.backend.domain.model.Planet;
import com.alvaroeb.backend.domain.port.PlanetsService;
import com.alvaroeb.backend.infrastructure.controller.dto.QueryParams;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planets")
public class PlanetsController {
    private final PlanetsService planetsService;

    public PlanetsController(PlanetsService planetsService) {
        this.planetsService = planetsService;
    }

    @GetMapping
    public Page<Planet> getPlanets(@ModelAttribute QueryParams queryParams) {
        return planetsService.getPlanets(
                queryParams.getPage(),
                queryParams.getSize(),
                queryParams.getSearch(),
                queryParams.getSort(),
                queryParams.getDirection()
        );
    }
}