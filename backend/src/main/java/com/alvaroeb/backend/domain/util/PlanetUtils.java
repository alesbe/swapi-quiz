package com.alvaroeb.backend.domain.util;

import com.alvaroeb.backend.domain.model.Planet;

public class PlanetUtils {

    public static boolean isUnknown(Planet planet) {
        if (planet == null)
            return true;

        return "unknown".equalsIgnoreCase(planet.getName())
                && planet.getRotationPeriod() != null && planet.getRotationPeriod() == 0
                && planet.getOrbitalPeriod() != null && planet.getOrbitalPeriod() == 0
                && planet.getDiameter() != null && planet.getDiameter() == 0
                && "unknown".equalsIgnoreCase(planet.getClimate())
                && "unknown".equalsIgnoreCase(planet.getGravity())
                && "unknown".equalsIgnoreCase(planet.getTerrain())
                && planet.getSurfaceWater() == null
                && planet.getPopulation() == null;
    }
}
