package com.courseapp.backend.services;

import com.courseapp.backend.repositories.IGenericRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<E, D> implements BaseService<D>{

    @Autowired
    protected ModelMapper modelMapper;

    protected abstract IGenericRepository<E, Long> getRepositoryInstance();
    public abstract E convertToEntity(D dto);
    public abstract D convertToDTO(E entity);
    protected abstract boolean isEntityExistsAndMatchId(long id, Optional<D> dto);

    @Override
    public Iterable<D> findAll() {
        return getRepositoryInstance().findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<D> findById(long id) {
        return getRepositoryInstance().findById(id).map(this::convertToDTO);
    }

    @Transactional
    @Override
    public Optional<D> save(Optional<D> dto) {
        return dto.map(o -> convertToDTO(getRepositoryInstance().save(convertToEntity(o))));
    }

    @Transactional
    @Override
    public Optional<D> update(long id, Optional<D> dto) {
        if (dto.isPresent() && isEntityExistsAndMatchId(id, dto)) {
            return dto.map(o -> convertToDTO(getRepositoryInstance().save(convertToEntity(o))));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<D> deleteById(long id) {
        if (getRepositoryInstance().existsById(id)) {
            Optional<D> deleted = getRepositoryInstance().findById(id).map(this::convertToDTO);
            getRepositoryInstance().deleteById(id);
            return deleted;
        }
        return Optional.empty();
    }
}
