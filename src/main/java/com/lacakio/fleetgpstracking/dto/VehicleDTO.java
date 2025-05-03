package com.lacakio.fleetgpstracking.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class VehicleDTO {
    @NotBlank
    private String plateNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String manufacturer;

    @NotBlank
    private String model;

    @NotNull
    private Integer year;
}
