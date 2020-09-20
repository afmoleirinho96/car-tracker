package com.automotive.tracker.to.rest;

import com.automotive.tracker.model.annotations.Json;
import lombok.Data;

@Data
@Json
public class VehicleDto {

    private String id;
    private String vin;
    private String owner;
    private String plateDate;
}
