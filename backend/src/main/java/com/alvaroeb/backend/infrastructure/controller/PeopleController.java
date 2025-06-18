package com.alvaroeb.backend.infrastructure.controller;

import com.alvaroeb.backend.infrastructure.controller.dto.QueryParams;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.alvaroeb.backend.domain.model.Person;
import com.alvaroeb.backend.domain.port.PeopleService;

@RestController
@RequestMapping("/api/people")
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public Page<Person> getPeople(@ModelAttribute QueryParams queryParams) {
        return peopleService.getPeople(
            queryParams.getPage(),
            queryParams.getSize(),
            queryParams.getSearch(),
            queryParams.getSort(),
            queryParams.getDirection()
        );
    }
}