package com.asr.sensors.converter;

import com.asr.sensors.db.model.Sensor;
import com.asr.sensors.dto.model.SensorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorConverter {

    @Autowired
    private SensorTypeConverter sensorTypeConverter;
    @Autowired
    private SensorUnitConverter sensorUnitConverter;
    @Autowired
    private RangeConverter rangeConverter;

    public Sensor convert(SensorDto request) {
        Sensor sensor = new Sensor();
        sensor.setName(request.getName());
        sensor.setModel(request.getModel());
        sensor.setRangeFrom(request.getRange().getFrom());
        sensor.setRangeTo(request.getRange().getTo());
        sensor.setSensorType(sensorTypeConverter.convert(request.getType()));
        sensor.setSensorUnit(sensorUnitConverter.convert(request.getUnit()));
        sensor.setLocation(request.getLocation());
        sensor.setDescription(request.getDescription());
        return sensor;
    }

    public SensorDto convert(Sensor sensor) {
        SensorDto sensorResponse = new SensorDto();
        sensorResponse.setId(sensor.getId());
        sensorResponse.setName(sensor.getName());
        sensorResponse.setModel(sensor.getModel());
        sensorResponse.setRange(rangeConverter.convert(sensor.getRangeFrom(), sensor.getRangeTo()));
        sensorResponse.setType(sensor.getSensorType().getName());
        sensorResponse.setUnit(sensor.getSensorUnit().getName());
        sensorResponse.setLocation(sensor.getLocation());
        sensorResponse.setDescription(sensor.getDescription());
        return sensorResponse;
    }
}
