package com.asr.sensors.controller;

import com.asr.sensors.converter.SensorConverter;
import com.asr.sensors.db.model.Sensor;
import com.asr.sensors.db.model.SensorType;
import com.asr.sensors.db.model.SensorUnit;
import com.asr.sensors.dto.model.SensorDto;
import com.asr.sensors.service.SensorService;
import com.asr.sensors.service.SensorTypeService;
import com.asr.sensors.service.SensorUnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorTypeService sensorTypeService;
    @Autowired
    private SensorUnitService sensorUnitService;
    @Autowired
    private SensorConverter sensorConverter;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getSensor(@PathVariable long id) {
        return new ResponseEntity<>(sensorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SensorDto> addSensor(@RequestBody @Valid SensorDto sensorDto) {
        Sensor sensor = getSensor(sensorDto);
        if (sensor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sensor save = sensorService.save(sensor);
        return new ResponseEntity<>(sensorConverter.convert(save), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateSensor(@PathVariable long id, @RequestBody @Valid SensorDto sensorDto) {
        Sensor sensor = getSensor(sensorDto);
        if (sensor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Sensor update = sensorService.update(id, sensor);
        return new ResponseEntity<>(sensorConverter.convert(update), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteSensor(@PathVariable long id) {
        Sensor delete = sensorService.deleteById(id);
        return new ResponseEntity<>(sensorConverter.convert(delete), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_ADMIN')")
    public List<SensorDto> getSensorsByNameOrModel(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String model) {
        if (name == null && model == null) {
            return sensorService.findAll().stream().map(sensorConverter::convert).collect(Collectors.toList());
        } else if (name != null && model != null) {
            return sensorService.findByNameAndModel(name, model).stream().map(sensorConverter::convert).collect(Collectors.toList());
        } else if (name != null) {
            return sensorService.findByName(name).stream().map(sensorConverter::convert).collect(Collectors.toList());
        } else {
            return sensorService.findByModel(model).stream().map(sensorConverter::convert).collect(Collectors.toList());
        }
    }

    private Sensor getSensor(SensorDto sensorDto) {
        Sensor sensor = sensorConverter.convert(sensorDto);

        // Type and Unit should be in DB
        SensorType existingType = sensorTypeService.findByName(sensor.getSensorType().getName());
        String sensorUnitName = sensor.getSensorUnit().getName();
        SensorUnit existingUnit = sensorUnitService.findByName(sensorUnitName);
        if (existingType == null || (sensorUnitName != null && existingUnit == null)) {
            return null;
        }

        sensor.setSensorType(existingType);
        sensor.setSensorUnit(existingUnit);
        return sensor;
    }
}
