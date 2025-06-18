package com.alvaroeb.backend.infrastructure.mapper;

import com.alvaroeb.backend.domain.model.Person;
import com.alvaroeb.backend.domain.model.Planet;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { MapperUtils.class, PlanetResolver.class })
public interface PersonMapper {

    @Mapping(source = "height", target = "height", qualifiedByName = "parseIntOrNull")
    @Mapping(source = "mass", target = "mass", qualifiedByName = "parseIntOrNull")
    @Mapping(source = "created", target = "created", qualifiedByName = "mapSwapiDate")
    @Mapping(source = "edited", target = "edited", qualifiedByName = "mapSwapiDate")
    @Mapping(source = "homeworld", target = "homeworld") // PlanetResolver se encarga de filtrar unknowns
    Person toPerson(SwapiPersonDTO dto);
}
