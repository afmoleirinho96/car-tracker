package com.automotive.tracker.services;

import com.automotive.tracker.exceptions.VehicleNotFoundException;
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

    public Optional<Vehicle> getVehicle(String vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void updateVehicleVin(String vehicleId, String vin) {
        Vehicle vehicle = getVehicle(vehicleId)
            .orElseThrow(VehicleNotFoundException.supplyVehicleNotFound(vehicleId));

        vehicle.setVin(vin);
    }

    public void deleteVehicle(String vehicleId) {
        if (!getVehicle(vehicleId).isPresent()) {
            throw VehicleNotFoundException.vehicleNotFound(vehicleId);
        }
        vehicleRepository.deleteById(vehicleId);
    }
}
