package com.courseapp.backend.services;

import com.courseapp.backend.repositories.IGenericRepository;
import io.micrometer.core.annotation.Timed;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public abstract class BaseServiceImpl<GenericEntity, GenericDTO> implements BaseService<GenericDTO, GenericEntity>{

    protected final ModelMapper modelMapper;

    public BaseServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected abstract IGenericRepository<GenericEntity, Long> getRepositoryInstance();
    protected abstract boolean isEntityExistsAndMatchId(long id, Optional<GenericDTO> dto);

    // TODO: something doesn't work, check that
    @Override
    //@Timed(value = "recipes.get.time", histogram = true, percentiles = {0.3, 0.6, 0.95})
    public Iterable<GenericDTO> findAll() {
        return convertToCollectionOfDTOs(getRepositoryInstance().findAll());
    }

    @Override
    public Iterable<GenericDTO> findAll(Pageable pageable) {
        return convertToCollectionOfDTOs(getRepositoryInstance().findAll(pageable).getContent());
    }

    @Override
    public Optional<GenericDTO> findById(long id) {
        return getRepositoryInstance().findById(id).map(this::convertToDTO);
    }

    @Override
    public Optional<GenericDTO> save(Optional<GenericDTO> dto) {
        return dto.map(o -> convertToDTO(getRepositoryInstance().save(convertToEntity(o))));
    }

    @Override
    public Optional<GenericDTO> update(long id, Optional<GenericDTO> dto) {
        if (dto.isPresent() && isEntityExistsAndMatchId(id, dto)) {
            return dto.map(o -> convertToDTO(getRepositoryInstance().save(convertToEntity(o))));
        }
        return Optional.empty();
    }

    @Override
    public Optional<GenericDTO> deleteById(long id) {
        if (getRepositoryInstance().existsById(id)) {
            Optional<GenericDTO> deleted = getRepositoryInstance().findById(id).map(this::convertToDTO);
            getRepositoryInstance().deleteById(id);
            return deleted;
        }
        return Optional.empty();
    }

    @Override
    public Iterable<GenericDTO> saveAll(Iterable<GenericDTO> dto) {
        return convertToCollectionOfDTOs(getRepositoryInstance().saveAll(convertToCollectionOfEntities(dto)));
    }

}
