package com.automotive.tracker.services;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ApplicationReadyListener {

    private final VehicleCreator vehicleCreator;

    public ApplicationReadyListener(VehicleCreator vehicleCreator) {
        this.vehicleCreator = vehicleCreator;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        vehicleCreator.retrieveVehicles();
    }
}
