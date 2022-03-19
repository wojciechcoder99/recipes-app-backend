package com.courseapp.backend.services.recipe;

import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.model.recipe.RecipeDTO;
import com.courseapp.backend.repositories.RecipeRepository;
import com.courseapp.backend.services.ConvertToEntityOrDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService, ConvertToEntityOrDto<Recipe, RecipeDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Iterable<RecipeDTO> findAll() {
        return recipeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RecipeDTO> findById(long id) {
        return recipeRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional
    @Override
    public Optional<RecipeDTO> save(Optional<RecipeDTO> recipe) {
        return recipe.map(o -> convertToDTO(recipeRepository.save(convertToEntity(o))));
    }

    @Transactional
    @Override
    public Optional<RecipeDTO> update(long id, Optional<RecipeDTO> recipe) {
        if (recipe.isPresent() &&
                recipeRepository.existsById(recipe.get().getId()) &&
                recipe.get().getId() == id) {
             return recipe.map(o -> convertToDTO(recipeRepository.save(convertToEntity(o))));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<RecipeDTO> deleteById(long id) {
        if (recipeRepository.existsById(id)) {
            Optional<RecipeDTO> deleted = recipeRepository.findById(id).map(this::convertToDTO);
            recipeRepository.deleteById(id);
            return deleted;
        }
        return Optional.empty();
    }

    @Override
    public Recipe convertToEntity(RecipeDTO recipe) {
        return modelMapper.map(recipe, Recipe.class);
    }

    @Override
    public RecipeDTO convertToDTO(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDTO.class);
    }
}
