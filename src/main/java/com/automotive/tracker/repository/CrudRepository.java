package com.automotive.tracker.repository;

import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("squid:S00119")
public interface CrudRepository<T, ID> {

    <S extends T> S save(S entity);

    Stream<T> findAll();

    Optional<T> findById(ID id);

    void deleteById(ID id);

}
