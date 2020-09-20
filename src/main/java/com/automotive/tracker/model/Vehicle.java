package com.automotive.tracker.model;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Vehicle {

    private String id;
    private String vin;
    private String owner;
    @Nullable
    private String plateDate;
}
