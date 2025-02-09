package com.asr.sensors.repository;

import com.asr.sensors.db.model.SensorUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorUnitRepository  extends JpaRepository<SensorUnit, Long> {
    SensorUnit findByName(String name);
}
