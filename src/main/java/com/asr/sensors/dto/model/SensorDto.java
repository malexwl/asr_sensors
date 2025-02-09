package com.asr.sensors.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDto {

    private Long id;
    @NotBlank(message = "name field is required.")
    @Size(min = 3, max = 30, message = "The length of name field must be between 3 and 30 characters.")
    private String name;
    @NotBlank(message = "model field is required.")
    @Size(max = 15, message = "The length of model field must less than 15 characters.")
    private String model;
    private RangeDto range;
    @NotBlank(message = "type field is required.")
    private String type;
    private String unit;
    @Size(max = 40, message = "The length of location field must less than 40 characters.")
    private String location;
    @Size(max = 200, message = "The length of description field must less than 200 characters.")
    private String description;
}
