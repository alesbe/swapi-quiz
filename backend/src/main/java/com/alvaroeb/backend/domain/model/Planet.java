package com.alvaroeb.backend.domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Planet {
    private String name;
    private Integer rotationPeriod;
    private Integer orbitalPeriod;
    private Integer diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private Integer surfaceWater;
    private Long population;
    private LocalDateTime created;
    private LocalDateTime edited;
}
