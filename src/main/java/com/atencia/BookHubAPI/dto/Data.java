package com.atencia.BookHubAPI.dto;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(List<DataBooks> results) {
}
