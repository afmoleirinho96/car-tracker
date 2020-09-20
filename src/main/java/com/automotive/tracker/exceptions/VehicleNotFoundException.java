package com.automotive.tracker.exceptions;

import java.util.function.Supplier;

public final class VehicleNotFoundException extends RuntimeException {

    private static final String VEHICLE_WITH_VIN = "Vehicle with Vin ";
    private static final String VEHICLE_WITH_ID = "Vehicle with id ";
    private static final String DOES_NOT_EXIST = " does not exist";
    private static final String VIN_DECODE_API_ERROR = "VIN Decode API was unable to retrieve info for ";

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
        return () -> vinDecodeNotFound(vin);
    }

    public static CTNotFoundException vinDecodeNotFound(String vin) {
        return new CTNotFoundException(VIN_DECODE_API_ERROR + VEHICLE_WITH_VIN + vin);
    }

}