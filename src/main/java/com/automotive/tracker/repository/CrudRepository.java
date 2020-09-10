package com.automotive.tracker.repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface CrudRepository<T, ID> {

    <S extends T> S save(S entity);

    // iterable in CrudRepository
    Stream<T> findAll();

    // avoid the use of null checks
    Optional<T> findById(ID id);

}
