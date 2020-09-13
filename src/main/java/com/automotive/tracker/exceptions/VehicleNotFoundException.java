package com.automotive.tracker.exceptions;

import java.util.function.Supplier;


public final class VehicleNotFoundException {

    public VehicleNotFoundException() {
    }

    public static Supplier<CTNotFoundException> supplyVehicleNotFound(String vehicleId) {
        return () -> vehicleNotFound(vehicleId);
    }

    public static CTNotFoundException vehicleNotFound(String vehicleId) {
        return new CTNotFoundException("Vehicle with id " + vehicleId + " does not exist");
    }

}