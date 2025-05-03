package com.lacakio.fleetgpstracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FleetGpsTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleetGpsTrackingApplication.class, args);
    }

}
