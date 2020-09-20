package com.automotive.tracker.repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface CrudRepository<T, Id> {

    <S extends T> S save(S entity);

    // iterable in CrudRepository
    Stream<T> findAll();

    // avoid the use of null checks
    Optional<T> findById(Id id);

    void deleteById(Id id);

    void deleteAll();

}
