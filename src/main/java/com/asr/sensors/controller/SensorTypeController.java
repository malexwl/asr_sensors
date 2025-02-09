package com.asr.sensors.controller;

import com.asr.sensors.converter.SensorTypeConverter;
import com.asr.sensors.db.model.SensorType;
import com.asr.sensors.dto.model.SensorTypeDto;
import com.asr.sensors.service.SensorTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sensortype")
public class SensorTypeController {

    @Autowired
    public SensorTypeService sensorTypeService;
    @Autowired
    private SensorTypeConverter sensorTypeConverter;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSensorType(@PathVariable long id) {
        return new ResponseEntity<>(sensorTypeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SensorTypeDto> addSensorType(@RequestBody @Valid SensorTypeDto sensorTypeDto) {
        SensorType sensorType = sensorTypeConverter.convert(sensorTypeDto);
        SensorType save = sensorTypeService.save(sensorType);
        return new ResponseEntity<>(sensorTypeConverter.convert(save), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSensorType(@PathVariable long id, @RequestBody @Valid SensorTypeDto sensorTypeDto) {
        SensorType sensorType = sensorTypeConverter.convert(sensorTypeDto);
        SensorType update = sensorTypeService.update(id, sensorType);
        return new ResponseEntity<>(sensorTypeConverter.convert(update), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSensortype(@PathVariable long id) {
        SensorType delete = sensorTypeService.deleteById(id);
        return new ResponseEntity<>(sensorTypeConverter.convert(delete), HttpStatus.OK);
    }

    @GetMapping
    public List<SensorTypeDto> getSensorTypes(@RequestParam(required = false) String name) {
        if (name == null) {
            return sensorTypeService.findAll().stream().map(sensorTypeConverter::convert).collect(Collectors.toList());
        } else {
            return Collections.singletonList(sensorTypeConverter.convert(sensorTypeService.findByName(name)));
        }
    }
}
