package com.lacakio.fleetgpstracking.service.impl;

import com.lacakio.fleetgpstracking.dto.VehicleDTO;
import com.lacakio.fleetgpstracking.entity.Vehicle;
import com.lacakio.fleetgpstracking.repository.VehicleRepository;
import com.lacakio.fleetgpstracking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    @Override
    public void saveVehicle(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber(dto.getPlateNumber());
        vehicle.setName(dto.getName());
        vehicle.setType(dto.getType());
        vehicle.setManufacturer(dto.getManufacturer());
        vehicle.setModel(dto.getModel());
        vehicle.setYear(dto.getYear());

        vehicleRepository.save(vehicle);
    }
}
