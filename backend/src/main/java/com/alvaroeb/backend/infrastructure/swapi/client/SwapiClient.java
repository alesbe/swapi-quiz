package com.alvaroeb.backend.infrastructure.swapi.client;

import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPeopleResponse;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPersonDTO;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPlanetDTO;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPlanetsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class SwapiClient {

    private final WebClient webClient;

    public SwapiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<SwapiPersonDTO> getAllPeople() {
        List<SwapiPersonDTO> people = new ArrayList<>();
        String url = "/people/";
        while (url != null) {
            SwapiPeopleResponse response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(SwapiPeopleResponse.class)
                    .block();

            if (response != null) {
                people.addAll(response.getResults());
                url = extractRelativePath(response.getNext());
            } else {
                url = null;
            }
        }
        return people;
    }

    public List<SwapiPlanetDTO> getAllPlanets() {
        List<SwapiPlanetDTO> planets = new ArrayList<>();
        String url = "/planets/";
        while (url != null) {
            SwapiPlanetsResponse response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(SwapiPlanetsResponse.class)
                    .block();

            if (response != null) {
                planets.addAll(response.getResults());
                url = extractRelativePath(response.getNext());
            } else {
                url = null;
            }
        }
        return planets;
    }

    public SwapiPlanetDTO fetchPlanetFromUrl(String url) {
        String relativePath = extractRelativePath(url);

        return webClient.get()
                .uri(relativePath)
                .retrieve()
                .bodyToMono(SwapiPlanetDTO.class)
                .block();
    }

    private String extractRelativePath(String fullUrl) {
        if (fullUrl == null) return null;
        int index = fullUrl.indexOf("/api/");
        return (index != -1) ? fullUrl.substring(index + 4) : null; // Removes "/api"
    }
}
