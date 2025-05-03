package com.lacakio.fleetgpstracking.repository;

import com.lacakio.fleetgpstracking.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
