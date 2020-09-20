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

    public Optional<Vehicle> getVehicleById(String vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    /*
        Could do in a easier, throwing supplyException if vehicle not found,
        otherwise delegating repository to delete it, without returning the vehicle erased.
     */
    public Vehicle deleteVehicleById(String vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(vehicle.get().getId());
            return vehicle.get();
        }

        throw (VehicleNotFoundException.vehicleNotFound(vehicleId));
    }
}
