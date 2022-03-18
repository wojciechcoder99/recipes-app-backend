package com.courseapp.backend.services;

public interface ConvertToEntityOrDto<E, D> {

    /**
     * Convert receives dto object to entity object
     * @param dto
     * @return entity
     */
     E convertToEntity(D dto);

    /**
     * Convert receives entity object to dto object
     * @param entity
     * @return dto
     */
     D convertToDTO(E entity);
}
