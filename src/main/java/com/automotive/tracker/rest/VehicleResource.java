package com.automotive.tracker.rest;

import com.automotive.tracker.exceptions.VehicleNotFoundException;
import com.automotive.tracker.mapper.VehicleMapper;
import com.automotive.tracker.model.Vehicle;
import com.automotive.tracker.services.VehicleService;
import com.automotive.tracker.to.rest.VehicleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return vehicleService.getVehicleById(id)
                             .map(vehicleMapper::map)
                             .orElseThrow(VehicleNotFoundException.supplyVehicleNotFound(id));
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(vehicleService.createVehicle(vehicle), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable String id) {
        return new ResponseEntity<>(vehicleService.deleteVehicleById(id), HttpStatus.OK);
    }

}
