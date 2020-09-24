package com.automotive.tracker.repository;

import com.automotive.tracker.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    private final Map<String, Vehicle> storage = new ConcurrentHashMap<>();

    @Override
    public <S extends Vehicle> S save(S entity) {
        String vehicleId = String.valueOf(UUID.randomUUID());
        entity.setId(vehicleId);
        storage.put(vehicleId, entity);

        return entity;
    }

    @Override
    public Stream<Vehicle> findAll() {
        return storage.values().stream().filter(Objects::nonNull);
    }

    @Override
    public Optional<Vehicle> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }
}
