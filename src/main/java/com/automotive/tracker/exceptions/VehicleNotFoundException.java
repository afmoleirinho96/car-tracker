package com.automotive.tracker.exceptions;

import java.util.function.Supplier;

public final class VehicleNotFoundException {

    private static final String VEHICLE_WITH_VIN = "Vehicle with Vin ";
    private static final String VEHICLE_WITH_ID = "Vehicle with id ";
    private static final String DOES_NOT_EXIST = " does not exist";

    public static Supplier<CTNotFoundException> supplyVehicleNotFound(String vehicleId) {
        return () -> vehicleNotFound(vehicleId);
    }

    public static CTNotFoundException vehicleNotFound(String vehicleId) {
        return new CTNotFoundException(VEHICLE_WITH_ID + vehicleId + DOES_NOT_EXIST);
    }

    public static Supplier<CTNotFoundException> supplyVehicleWithVinNotFound(String vin) {
        return () -> vehicleWithVinNotFound(vin);
    }

    public static CTNotFoundException vehicleWithVinNotFound(String vin) {
        return new CTNotFoundException(VEHICLE_WITH_VIN + vin + DOES_NOT_EXIST);
    }

    public static Supplier<CTNotFoundException> supplyVinDecodeNotFound(String vin) {
        return () -> VinDecodeNotFound(vin);
    }

    public static CTNotFoundException VinDecodeNotFound(String vin) {
        return new CTNotFoundException("Vin Decode for" + VEHICLE_WITH_VIN + " " + vin + DOES_NOT_EXIST);
    }

}