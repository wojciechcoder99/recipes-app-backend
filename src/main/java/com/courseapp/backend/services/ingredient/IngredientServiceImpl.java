package com.courseapp.backend.services.ingredient;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.repositories.IGenericRepository;
import com.courseapp.backend.repositories.IngredientRepository;
import com.courseapp.backend.services.BaseServiceImpl;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IngredientServiceImpl extends BaseServiceImpl<Ingredient, IngredientDTO> {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(ModelMapper modelMapper, IngredientRepository ingredientRepository) {
        super(modelMapper);
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    protected IGenericRepository<Ingredient, Long> getRepositoryInstance() {
        return ingredientRepository;
    }

    @Override
    protected boolean isEntityExistsAndMatchId(long id, IngredientDTO dto) {
        return ingredientRepository.existsById(id) && dto.getId() == id;
    }

    @Override
    public Ingredient convertToEntity(IngredientDTO ingredientDTO) {
        return modelMapper.map(ingredientDTO, Ingredient.class);
    }

    @Override
    public IngredientDTO convertToDTO(Ingredient ingredient) {
        return modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public Iterable<Ingredient> convertToCollectionOfEntities(Iterable<IngredientDTO> dtosCollection) {
        return StreamSupport.stream(dtosCollection.spliterator(), false)
                        .map(this::convertToEntity)
                        .collect(Collectors.toList());
    }

    @Override
    public Iterable<IngredientDTO> convertToCollectionOfDTOs(Iterable<Ingredient> entitiesCollection) {
        return StreamSupport.stream(entitiesCollection.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
