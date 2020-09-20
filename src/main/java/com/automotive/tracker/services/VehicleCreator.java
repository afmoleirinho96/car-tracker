package com.automotive.tracker.services;

import com.automotive.tracker.model.Vehicle;
import com.automotive.tracker.repository.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

@Service
public class VehicleCreator {

    private final String vehicleConfigFile;
    private final ObjectMapper objectMapper;
    private final VehicleRepository vehicleRepository;

    public VehicleCreator(@Value("${automotive.vehicleTracker.services.vehicleConfigFile}") String vehicleConfigFile,
                          ObjectMapper objectMapper,
                          VehicleRepository vehicleRepository
    ) {
        this.vehicleConfigFile = vehicleConfigFile;
        this.objectMapper = objectMapper;
        this.vehicleRepository = vehicleRepository;
    }

    @SuppressWarnings("unchecked")
    public void retrieveVehicles() {
        InputStream resource = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(this.vehicleConfigFile), "UTF-8");

        try {
            Arrays.stream(objectMapper.readValue(resource, Vehicle[].class))
                  .forEach(this::createVehicle);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    private void createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
