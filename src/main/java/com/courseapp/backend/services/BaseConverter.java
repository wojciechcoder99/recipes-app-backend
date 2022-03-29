package com.courseapp.backend.services;

public interface BaseConverter<GenericDTO,GenericEntity> {
    GenericEntity convertToEntity(GenericDTO dto);
    GenericDTO convertToDTO(GenericEntity entity);
    Iterable<GenericEntity> convertToCollectionOfEntities(Iterable<GenericDTO> dtosCollection);
    Iterable<GenericDTO> convertToCollectionOfDTOs(Iterable<GenericEntity> entitiesCollection);
}
