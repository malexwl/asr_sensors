package com.asr.sensors.repository;

import com.asr.sensors.db.model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorTypeRepository  extends JpaRepository<SensorType, Long> {
    public SensorType findByName(String name);
}
