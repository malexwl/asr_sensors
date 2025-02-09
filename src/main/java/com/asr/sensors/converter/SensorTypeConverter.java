package com.asr.sensors.converter;

import com.asr.sensors.db.model.SensorType;
import com.asr.sensors.dto.model.SensorTypeDto;
import org.springframework.stereotype.Component;

@Component
public class SensorTypeConverter {

    public SensorType convert(SensorTypeDto sensorTypeDto) {
        return convert(sensorTypeDto.getName());
    }

    public SensorType convert(String sensorTypeName) {
        SensorType sensorType = new SensorType();
        sensorType.setName(sensorTypeName);
        return sensorType;
    }

    public SensorTypeDto convert(SensorType sensorType) {
        SensorTypeDto sensorTypeDto = new SensorTypeDto();
        sensorTypeDto.setId(sensorType.getId());
        sensorTypeDto.setName(sensorType.getName());
        return sensorTypeDto;
    }
}
