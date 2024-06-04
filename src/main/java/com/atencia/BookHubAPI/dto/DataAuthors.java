package com.atencia.BookHubAPI.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DataAuthors(
        String name,
        @JsonAlias("birth_year") int birthYear,
        @JsonAlias("death_year") int deathYear) {
}

