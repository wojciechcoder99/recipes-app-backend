package com.courseapp.backend.services;

import java.util.Optional;

public interface BaseService<D> {
    Iterable<D> findAll();
    Optional<D> findById(long id);
    Optional<D> save(Optional<D> recipe);
    Optional<D> update(long id, Optional<D> recipe);
    Optional<D> deleteById(long id);
}
