package com.courseapp.backend.services.ingredient;


import com.courseapp.backend.model.ingredient.IngredientDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IngredientService {
    Iterable<IngredientDTO> findAll();
    Optional<IngredientDTO> findById(long id);
    Optional<IngredientDTO> save(Optional<IngredientDTO> ing);
    Optional<IngredientDTO> update(long id, Optional<IngredientDTO> ing);
    Optional<IngredientDTO> deleteById(long id);
}
