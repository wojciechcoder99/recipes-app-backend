package com.courseapp.backend.services;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<GenericDTO, GenericEntity> extends
        BaseConverter<GenericDTO, GenericEntity> {
    Iterable<GenericDTO> findAll();
    Iterable<GenericDTO> findAll(Pageable pageable);
    Optional<GenericDTO> findById(long id);
    Optional<GenericDTO> save(GenericDTO dto);
    Optional<GenericDTO> update(long id, GenericDTO dto);
    Optional<GenericDTO> deleteById(long id);
    Iterable<GenericDTO> saveAll(Iterable<GenericDTO> dto);
}
