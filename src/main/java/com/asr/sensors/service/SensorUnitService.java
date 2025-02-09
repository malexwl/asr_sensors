package com.asr.sensors.service;

import com.asr.sensors.db.model.SensorUnit;
import com.asr.sensors.repository.SensorUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorUnitService {

    @Autowired
    private SensorUnitRepository sensorUnitRepository;

    public SensorUnit save(SensorUnit sensorUnit) {
        return sensorUnitRepository.save(sensorUnit);
    }

    public SensorUnit findById(Long id) {
        return sensorUnitRepository.findById(id).orElse(null);
    }

    public List<SensorUnit> findAll() {
        return sensorUnitRepository.findAll();
    }

    public SensorUnit deleteById(Long id) {
        SensorUnit existingSensorUnit = sensorUnitRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor Unit Not Found"));
        sensorUnitRepository.deleteById(id);
        return existingSensorUnit;
    }

    public SensorUnit update(Long id, SensorUnit sensorUnit) {
        SensorUnit existingSensorType = sensorUnitRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor Unit Not Found"));
        existingSensorType.setName(sensorUnit.getName());
        return sensorUnitRepository.save(existingSensorType);
    }

    public SensorUnit findByName(String name) {
        return sensorUnitRepository.findByName(name);
    }
}
