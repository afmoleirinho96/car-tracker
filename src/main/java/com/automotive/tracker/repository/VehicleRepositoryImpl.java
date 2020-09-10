package com.automotive.tracker.repository;

import com.automotive.tracker.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    private final Map<String, Vehicle> store = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong(0);

    @Override
    public <S extends Vehicle> S save(S entity) {
        String vehicleId = String.valueOf(currentId.getAndIncrement());
        entity.setId(vehicleId);
        System.out.println(entity);
        store.put(vehicleId, entity);
        return entity;
    }

    @Override
    public Stream<Vehicle> findAll() {
        return null;
    }

    @Override
    public Optional<Vehicle> findById(String s) {
        return Optional.empty();
    }
}
