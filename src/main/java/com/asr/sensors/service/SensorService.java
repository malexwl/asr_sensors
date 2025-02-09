package com.asr.sensors.service;

import com.asr.sensors.db.model.Sensor;
import com.asr.sensors.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Sensor findById(Long id) {
        return sensorRepository.findById(id).orElse(null);
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor deleteById(Long id) {
        Sensor existingSensor = sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor Not Found"));
        sensorRepository.deleteById(id);
        return existingSensor;
    }

    public Sensor update(Long id, Sensor sensor) {
        Sensor existingSensor = sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor Not Found"));
        existingSensor.setName(sensor.getName());
        existingSensor.setModel(sensor.getModel());
        existingSensor.setRangeFrom(sensor.getRangeFrom());
        existingSensor.setRangeTo(sensor.getRangeTo());
        existingSensor.setSensorType(sensor.getSensorType());
        existingSensor.setSensorUnit(sensor.getSensorUnit());
        existingSensor.setDescription(sensor.getDescription());
        return sensorRepository.save(existingSensor);
    }

    public List<Sensor> findByName(String name) {
        return sensorRepository.findByNameContainsIgnoreCase(name);
    }

    public List<Sensor> findByModel(String model) {
        return sensorRepository.findByModelContainsIgnoreCase(model);
    }

    public List<Sensor> findByNameAndModel(String name, String model) {
        return sensorRepository.findByNameAndModelContainsIgnoreCase(name, model);
    }
}
