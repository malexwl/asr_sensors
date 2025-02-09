package com.asr.sensors.db.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sensor_units")
@Data
public class SensorUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
}
