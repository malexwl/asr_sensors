package com.asr.sensors.controller;

import com.asr.sensors.converter.SensorUnitConverter;
import com.asr.sensors.db.model.SensorUnit;
import com.asr.sensors.dto.model.SensorUnitDto;
import com.asr.sensors.service.SensorUnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sensorunit")
public class SensorUnitController {

    @Autowired
    public SensorUnitService sensorUnitService;
    @Autowired
    private SensorUnitConverter sensorUnitConverter;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSensorUnit(@PathVariable long id) {
        return new ResponseEntity<>(sensorUnitService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SensorUnitDto> addSensorUnit(@RequestBody @Valid SensorUnitDto sensorUnitDto) {
        SensorUnit sensorUnit = sensorUnitConverter.convert(sensorUnitDto);
        SensorUnit save = sensorUnitService.save(sensorUnit);
        return new ResponseEntity<>(sensorUnitConverter.convert(save), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSensorUnit(@PathVariable long id, @RequestBody @Valid SensorUnitDto sensorUnitDto) {
        SensorUnit sensorUnit = sensorUnitConverter.convert(sensorUnitDto);
        SensorUnit update = sensorUnitService.update(id, sensorUnit);
        return new ResponseEntity<>(sensorUnitConverter.convert(update), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSensorUnit(@PathVariable long id) {
        SensorUnit delete = sensorUnitService.deleteById(id);
        return new ResponseEntity<>(sensorUnitConverter.convert(delete), HttpStatus.OK);
    }

    @GetMapping
    public List<SensorUnitDto> getSensorUnits(@RequestParam(required = false) String name) {
        if (name == null) {
            return sensorUnitService.findAll().stream().map(sensorUnitConverter::convert).collect(Collectors.toList());
        } else {
            return Collections.singletonList(sensorUnitConverter.convert(sensorUnitService.findByName(name)));
        }
    }
}
