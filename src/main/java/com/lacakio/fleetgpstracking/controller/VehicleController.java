package com.lacakio.fleetgpstracking.controller;

import com.lacakio.fleetgpstracking.dto.VehicleDTO;
import com.lacakio.fleetgpstracking.service.VehicleService;
import com.lacakio.fleetgpstracking.util.ApiResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicle")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody VehicleDTO request) {
        vehicleService.saveVehicle(request);
        return ApiResponseUtil.successResponse("Vehicle saved");
    }
}
