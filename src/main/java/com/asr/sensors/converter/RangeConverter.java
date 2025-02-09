package com.asr.sensors.converter;

import com.asr.sensors.dto.model.RangeDto;
import org.springframework.stereotype.Component;

@Component
public class RangeConverter {

    public RangeDto convert(Double from, Double to) {
        RangeDto dto = new RangeDto();
        dto.setFrom(from);
        dto.setTo(to);
        return dto;
    }
}
