package com.alvaroeb.backend.infrastructure.controller.dto;

import lombok.Data;

@Data
public class QueryParams {
    private int page = 0;
    private int size = 15;
    private String search = "";
    private String sort = "name";
    private String direction = "asc";
}
