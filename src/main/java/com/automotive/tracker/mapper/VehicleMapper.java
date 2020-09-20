package com.automotive.tracker.mapper;

import com.automotive.tracker.model.Vehicle;
import com.automotive.tracker.to.rest.VehicleDto;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface VehicleMapper {

    VehicleDto map(Vehicle vehicle);
}
