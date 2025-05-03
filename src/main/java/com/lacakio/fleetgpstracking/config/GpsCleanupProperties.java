package com.lacakio.fleetgpstracking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gps.logs.cleanup")
@Getter
@Setter
public class GpsCleanupProperties {

    private int days;

}
