package com.courseapp.backend.services.recipe;

import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.model.recipe.RecipeDTO;
import com.courseapp.backend.repositories.IGenericRepository;
import com.courseapp.backend.repositories.RecipeRepository;
import com.courseapp.backend.services.BaseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeServiceImpl extends BaseServiceImpl<Recipe, RecipeDTO> {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    protected IGenericRepository<Recipe, Long> getRepositoryInstance() {
        return recipeRepository;
    }

    @Override
    public Recipe convertToEntity(RecipeDTO dto) {
        return modelMapper.map(dto, Recipe.class);
    }

    @Override
    public RecipeDTO convertToDTO(Recipe entity) {
        return modelMapper.map(entity, RecipeDTO.class);
    }

    @Override
    protected boolean isEntityExistsAndMatchId(long id, Optional<RecipeDTO> dto) {
        return recipeRepository.existsById(id) && dto.get().getId() == id;
    }
}
