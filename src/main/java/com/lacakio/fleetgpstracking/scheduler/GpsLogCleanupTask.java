package com.lacakio.fleetgpstracking.scheduler;

import com.lacakio.fleetgpstracking.config.GpsCleanupProperties;
import com.lacakio.fleetgpstracking.repository.GPSLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class GpsLogCleanupTask {

    private final GPSLogRepository gpsLogRepository;
    private final GpsCleanupProperties cleanupProperties;

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void cleanOldLogs() {
        int days = cleanupProperties.getDays();
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(days);
        int deleted = gpsLogRepository.deleteByTimestampBefore(cutoffDate);
        log.info(" Deleted {} GPS logs older than {} days (before {}).", deleted, days, cutoffDate);
    }

}
