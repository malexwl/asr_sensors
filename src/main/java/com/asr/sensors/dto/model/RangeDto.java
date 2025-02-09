package com.asr.sensors.dto.model;

import com.asr.sensors.dto.validator.ProperRange;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ProperRange(message = "'to' value should be less than 'from' value")
public class RangeDto {
    private Double from;
    @NotNull(message = "'to' field is required.")
    private Double to;
}
