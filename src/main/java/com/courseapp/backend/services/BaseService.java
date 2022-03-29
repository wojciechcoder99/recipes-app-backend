package com.courseapp.backend.services;

import java.util.Optional;

public interface BaseService<GenericDTO, GenericEntity> extends
        BaseConverter<GenericDTO, GenericEntity> {
    Iterable<GenericDTO> findAll();
    Optional<GenericDTO> findById(long id);
    Optional<GenericDTO> save(Optional<GenericDTO> dto);
    Optional<GenericDTO> update(long id, Optional<GenericDTO> dto);
    Optional<GenericDTO> deleteById(long id);
    Iterable<GenericDTO> saveAll(Iterable<GenericDTO> dto);
}
