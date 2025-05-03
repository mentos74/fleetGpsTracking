package com.lacakio.fleetgpstracking.controller;

import com.lacakio.fleetgpstracking.dto.GPSLogDTO;
import com.lacakio.fleetgpstracking.entity.GPSLog;
import com.lacakio.fleetgpstracking.service.GPSService;
import com.lacakio.fleetgpstracking.util.ApiResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


import org.springframework.data.domain.Sort;


@RestController
@RequestMapping("/api")
public class GPSController {

    @Autowired
    GPSService gpsService;


    @PostMapping("/gps")
    public ResponseEntity<?> postGPS(@Valid @RequestBody GPSLogDTO req) {
        gpsService.saveGpsLog(req);
        return ApiResponseUtil.successResponse("GPS log saved");
    }

    @GetMapping("/vehicles/{id}/last-location")
    public ResponseEntity<GPSLog> getLast(@PathVariable Long id) {
        return ResponseEntity.ok(gpsService.getLastLocation(id));
    }

    @GetMapping("/vehicles/{id}/history")
    public ResponseEntity<?> getHistory(@PathVariable Long id,
                                        @RequestParam String from,
                                        @RequestParam String to,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        HttpServletRequest request) {
        if (from == null || from.isBlank() || to == null || to.isBlank()) {
            return ApiResponseUtil.badRequest("from / to", "Invalid date format, expected yyyy-MM-dd'T'HH:mm:ss", from + " | " + to, request);
        }

        try {
            LocalDateTime fromDate = LocalDateTime.parse(from);
            LocalDateTime toDate = LocalDateTime.parse(to);

            Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
            return ResponseEntity.ok(gpsService.getHistory(id, fromDate, toDate, pageable));

        } catch (DateTimeParseException e) {
            return ApiResponseUtil.badRequest("from / to", "Invalid date format, expected yyyy-MM-dd'T'HH:mm:ss", from + " | " + to, request);
        }
    }
}