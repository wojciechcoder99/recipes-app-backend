package com.courseapp.backend.services.ingredient;


import com.courseapp.backend.model.ingredient.IngredientDTO;
import org.springframework.stereotype.Service;

public interface IngredientService {
    Iterable<IngredientDTO> findAll();
    IngredientDTO findById(long id);
    IngredientDTO save(IngredientDTO ing);
    IngredientDTO update(IngredientDTO ing);
    boolean deleteById(long id);
}
