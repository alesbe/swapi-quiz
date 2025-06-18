package com.alvaroeb.backend.application.util;

import com.alvaroeb.backend.domain.model.Planet;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

public class PlanetComparators {

    private static final Map<String, Comparator<Planet>> COMPARATORS = Map.ofEntries(
            Map.entry("name", Comparator.comparing(Planet::getName, Comparator.nullsLast(String::compareToIgnoreCase))),
            Map.entry("rotationPeriod",
                    Comparator.comparing(Planet::getRotationPeriod, Comparator.nullsLast(Integer::compareTo))),
            Map.entry("orbitalPeriod",
                    Comparator.comparing(Planet::getOrbitalPeriod, Comparator.nullsLast(Integer::compareTo))),
            Map.entry("diameter", Comparator.comparing(Planet::getDiameter, Comparator.nullsLast(Integer::compareTo))),
            Map.entry("climate",
                    Comparator.comparing(Planet::getClimate, Comparator.nullsLast(String::compareToIgnoreCase))),
            Map.entry("gravity",
                    Comparator.comparing(Planet::getGravity, Comparator.nullsLast(String::compareToIgnoreCase))),
            Map.entry("terrain",
                    Comparator.comparing(Planet::getTerrain, Comparator.nullsLast(String::compareToIgnoreCase))),
            Map.entry("surfaceWater",
                    Comparator.comparing(Planet::getSurfaceWater, Comparator.nullsLast(Integer::compareTo))),
            Map.entry("population", Comparator.comparing(Planet::getPopulation, Comparator.nullsLast(Long::compareTo))),
            Map.entry("created",
                    Comparator.comparing(Planet::getCreated, Comparator.nullsLast(java.time.LocalDateTime::compareTo))),
            Map.entry("edited",
                    Comparator.comparing(Planet::getEdited, Comparator.nullsLast(java.time.LocalDateTime::compareTo))));

    public static Comparator<Planet> getComparator(String sortBy, Sort.Direction direction) {
        String key = sortBy != null ? sortBy.toLowerCase(Locale.ROOT) : "name";

        Comparator<Planet> comparator = COMPARATORS.entrySet().stream()
                .filter(e -> e.getKey().equalsIgnoreCase(key))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(COMPARATORS.get("name"));

        return direction.isAscending() ? comparator : comparator.reversed();
    }
}
