package com.courseapp.backend.services.recipe;

import com.courseapp.backend.model.recipe.RecipeDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface RecipeService {
    Iterable<RecipeDTO> findAll();
    Optional<RecipeDTO> findById(long id);
    Optional<RecipeDTO> save(Optional<RecipeDTO> recipe);
    Optional<RecipeDTO> update(long id, Optional<RecipeDTO> recipe);
    Optional<RecipeDTO> deleteById(long id);
}
