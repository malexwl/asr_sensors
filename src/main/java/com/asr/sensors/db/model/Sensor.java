package com.asr.sensors.db.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sensors")
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 15)
    private String model;

    private Double rangeFrom;

    @Column(nullable = false)
    private Double rangeTo;

    @ManyToOne
    private SensorType sensorType;

    @ManyToOne
    private SensorUnit sensorUnit;

    @Column(length = 40)
    private String location;

    @Column(length = 200)
    private String description;
}
