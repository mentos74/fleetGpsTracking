package com.lacakio.fleetgpstracking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class GPSLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehicle vehicle;

    private Double latitude;

    private Double longitude;

    private Double speed;

    private LocalDateTime timestamp;

    private Boolean speedViolation = false;

}
