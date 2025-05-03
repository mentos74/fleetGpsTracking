package com.lacakio.fleetgpstracking.service.impl;

import com.lacakio.fleetgpstracking.dto.GPSLogDTO;
import com.lacakio.fleetgpstracking.entity.GPSLog;
import com.lacakio.fleetgpstracking.entity.Vehicle;
import com.lacakio.fleetgpstracking.exception.NotFoundException;
import com.lacakio.fleetgpstracking.repository.GPSLogRepository;
import com.lacakio.fleetgpstracking.repository.VehicleRepository;
import com.lacakio.fleetgpstracking.service.GPSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GPSServiceImpl implements GPSService {

    @Autowired
    VehicleRepository vehicleRepo;

    @Autowired
    GPSLogRepository gpsRepo;


    @Override
    public void saveGpsLog(GPSLogDTO dto) {
        Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId())
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        GPSLog log = new GPSLog();
        log.setVehicle(vehicle);
        log.setLatitude(dto.getLatitude());
        log.setLongitude(dto.getLongitude());
        log.setSpeed(dto.getSpeed());
        log.setTimestamp(Optional.ofNullable(dto.getTimestamp()).orElse(LocalDateTime.now()));
        log.setSpeedViolation(dto.getSpeed() > 100);

        gpsRepo.save(log);
    }

    @Override
    public GPSLog getLastLocation(Long id) {
        return gpsRepo.findTopByVehicleIdOrderByTimestampDesc(id)
                .orElseThrow(() -> new NotFoundException("No location found"));
    }

    @Override
    public Page<GPSLog> getHistory(Long id, LocalDateTime from, LocalDateTime to, Pageable pageable) {
        return gpsRepo.findByVehicleIdAndTimestampBetween(id, from, to,pageable);
    }
}
