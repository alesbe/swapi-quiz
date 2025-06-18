package com.alvaroeb.backend.infrastructure.mapper;

import com.alvaroeb.backend.domain.model.Planet;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPlanetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { MapperUtils.class })
public interface PlanetMapper {

    @Mapping(source = "rotationPeriod", target = "rotationPeriod", qualifiedByName = "parseIntOrNull")
    @Mapping(source = "orbitalPeriod", target = "orbitalPeriod", qualifiedByName = "parseIntOrNull")
    @Mapping(source = "diameter", target = "diameter", qualifiedByName = "parseIntOrNull")
    @Mapping(source = "surfaceWater", target = "surfaceWater", qualifiedByName = "parseIntOrNull")
    @Mapping(source = "population", target = "population", qualifiedByName = "parseLongOrNull")
    @Mapping(source = "created", target = "created", qualifiedByName = "mapSwapiDate")
    @Mapping(source = "edited", target = "edited", qualifiedByName = "mapSwapiDate")
    Planet toPlanet(SwapiPlanetDTO dto);
}
