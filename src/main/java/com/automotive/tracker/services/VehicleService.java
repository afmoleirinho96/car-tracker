package com.automotive.tracker.services;

import com.automotive.tracker.model.Vehicle;
import com.automotive.tracker.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Stream<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicle(String vehicleId){
        return vehicleRepository.findById(vehicleId);
    }
}
