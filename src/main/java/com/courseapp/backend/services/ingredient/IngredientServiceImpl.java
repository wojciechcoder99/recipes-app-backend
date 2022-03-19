package com.courseapp.backend.services.ingredient;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.repositories.IGenericRepository;
import com.courseapp.backend.repositories.IngredientRepository;
import com.courseapp.backend.services.BaseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl extends BaseServiceImpl<Ingredient, IngredientDTO> {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    protected IGenericRepository<Ingredient, Long> getRepositoryInstance() {
        return ingredientRepository;
    }

    @Override
    public Ingredient convertToEntity(IngredientDTO dto) {
        return modelMapper.map(dto, Ingredient.class);
    }

    @Override
    public IngredientDTO convertToDTO(Ingredient entity) {
        return modelMapper.map(entity, IngredientDTO.class);
    }

    @Override
    protected boolean isEntityExistsAndMatchId(long id, Optional<IngredientDTO> dto) {
        return ingredientRepository.existsById(id) && dto.get().getId() == id;
    }
}
