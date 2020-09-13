package com.automotive.tracker.rest;

import com.automotive.tracker.exceptions.VehicleNotFoundException;
import com.automotive.tracker.mapper.VehicleMapper;
import com.automotive.tracker.services.VehicleService;
import com.automotive.tracker.to.VehicleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vehicles")
public class VehicleResource {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    public VehicleResource(VehicleService vehicleService, VehicleMapper vehicleMapper) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    @GetMapping
    public List<VehicleDto> getAllVehicles() {
        return vehicleService.getVehicles()
                .map(vehicleMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public VehicleDto getVehicle(@PathVariable String id) {
        return vehicleService.getVehicle(id)
                .map(vehicleMapper::map)
                .orElseThrow(VehicleNotFoundException.supplyVehicleNotFound(id));
    }

}
