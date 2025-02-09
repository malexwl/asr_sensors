package com.asr.sensors.service;

import com.asr.sensors.db.model.SensorType;
import com.asr.sensors.repository.SensorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorTypeService {

    @Autowired
    private SensorTypeRepository sensorTypeRepository;

    public SensorType save(SensorType sensorType) {
        return sensorTypeRepository.save(sensorType);
    }

    public SensorType findById(Long id) {
        return sensorTypeRepository.findById(id).orElse(null);
    }

    public List<SensorType> findAll() {
        return sensorTypeRepository.findAll();
    }

    public SensorType deleteById(Long id) {
        SensorType existingSensorType = sensorTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor Type Not Found"));
        sensorTypeRepository.deleteById(id);
        return existingSensorType;
    }

    public SensorType update(Long id, SensorType sensorType) {
        SensorType existingSensorType = sensorTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor Type Not Found"));
        existingSensorType.setName(sensorType.getName());
        return sensorTypeRepository.save(existingSensorType);
    }

    public SensorType findByName(String name) {
        return sensorTypeRepository.findByName(name);
    }
}
