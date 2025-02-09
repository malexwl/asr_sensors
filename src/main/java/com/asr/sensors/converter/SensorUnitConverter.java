package com.asr.sensors.converter;

import com.asr.sensors.db.model.SensorUnit;
import com.asr.sensors.dto.model.SensorUnitDto;
import org.springframework.stereotype.Component;

@Component
public class SensorUnitConverter {

    public SensorUnit convert(SensorUnitDto sensorUnitDto) {
        return convert(sensorUnitDto.getName());
    }

    public SensorUnit convert(String sensorUnitName) {
        SensorUnit sensorUnit = new SensorUnit();
        sensorUnit.setName(sensorUnitName);
        return sensorUnit;
    }

    public SensorUnitDto convert(SensorUnit sensorUnit) {
        SensorUnitDto sensorUnitDto = new SensorUnitDto();
        sensorUnitDto.setId(sensorUnit.getId());
        sensorUnitDto.setName(sensorUnit.getName());
        return sensorUnitDto;
    }
}
