package com.asr.sensors.repository;

import com.asr.sensors.db.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

    List<Sensor> findByNameContainsIgnoreCase(String name);

    List<Sensor> findByModelContainsIgnoreCase(String name);

    List<Sensor> findByNameAndModelContainsIgnoreCase(String name, String model);
}
