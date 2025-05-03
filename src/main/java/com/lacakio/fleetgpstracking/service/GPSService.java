package com.lacakio.fleetgpstracking.service;

import com.lacakio.fleetgpstracking.dto.GPSLogDTO;
import com.lacakio.fleetgpstracking.entity.GPSLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;


public interface GPSService {

    public void saveGpsLog(GPSLogDTO dto);
    public GPSLog getLastLocation(Long id);
    Page<GPSLog> getHistory(Long id, LocalDateTime from, LocalDateTime to, Pageable pageable);

}
