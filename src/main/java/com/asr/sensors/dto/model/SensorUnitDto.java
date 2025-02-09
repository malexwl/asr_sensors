package com.asr.sensors.dto.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SensorUnitDto {
    private Long id;
    @NotBlank(message = "name field is required.")
    private String name;
}
