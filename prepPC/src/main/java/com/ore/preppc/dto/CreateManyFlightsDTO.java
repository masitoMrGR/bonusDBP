package com.ore.preppc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class CreateManyFlightsDTO {

    @JsonProperty("inputs")
    private List<NewFlightRequestDTO> flights;
}