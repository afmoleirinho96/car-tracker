package com.automotive.tracker.model;

import lombok.Data;

@Data
public class Vehicle {

    private String id;
    private String vin;
    private String owner;
    private String plateDate;
}
