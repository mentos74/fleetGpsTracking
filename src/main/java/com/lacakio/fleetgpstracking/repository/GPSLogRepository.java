package com.lacakio.fleetgpstracking.repository;

import com.lacakio.fleetgpstracking.entity.GPSLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GPSLogRepository extends JpaRepository<GPSLog, Long> {

    Page<GPSLog> findByVehicleIdAndTimestampBetween(Long id, LocalDateTime from, LocalDateTime to, Pageable pageable);
    Optional<GPSLog> findTopByVehicleIdOrderByTimestampDesc(Long vehicleId);
    Integer deleteByTimestampBefore(LocalDateTime threshold);

}
