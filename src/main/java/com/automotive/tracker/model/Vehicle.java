package com.automotive.tracker.model;

import jdk.internal.jline.internal.Nullable;
import lombok.Data;

@Data
public class Vehicle {

    private String id;
    private String vin;
    @Nullable
    private String owner;
    @Nullable
    private String plateDate;
}
