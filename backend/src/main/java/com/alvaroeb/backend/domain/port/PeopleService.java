package com.alvaroeb.backend.domain.port;

import org.springframework.data.domain.Page;

import com.alvaroeb.backend.domain.model.Person;

public interface PeopleService {
    Page<Person> getPeople(Integer page, Integer size, String search, String sort, String direction);
}
