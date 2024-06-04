package com.atencia.BookHubAPI.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DataBooks(
        String title,
        List<DataAuthors> authors,
        List<String> languages,
        @JsonAlias("download_count") Double download) {
}
