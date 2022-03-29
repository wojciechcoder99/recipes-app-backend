package com.courseapp.backend.services;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.model.recipe.RecipeDTO;
import com.courseapp.backend.repositories.IGenericRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
public abstract class BaseServiceImpl<GenericEntity, GenericDTO> implements BaseService<GenericDTO, GenericEntity>{

    @Autowired
    protected ModelMapper modelMapper;
    protected abstract IGenericRepository<GenericEntity, Long> getRepositoryInstance();
    protected abstract boolean isEntityExistsAndMatchId(long id, Optional<GenericDTO> dto);

    @Override
    public Iterable<GenericDTO> findAll() {
        return convertToCollectionOfDTOs(getRepositoryInstance().findAll());
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
