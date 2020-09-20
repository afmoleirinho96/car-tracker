package com.automotive.tracker.repository;

import com.automotive.tracker.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
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
        store.put(vehicleId, entity);
        return entity;
    }

    @Override
    public Stream<Vehicle> findAll() {
        return store.values().stream().filter(Objects::nonNull);
    }

    @Override
    public Optional<Vehicle> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

}
