package com.courseapp.backend.services.ingredient;

import com.courseapp.backend.model.ingredient.IngredientDTO;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Override
    public Iterable<IngredientDTO> findAll() {
        return null;
    }

    @Override
    public IngredientDTO findById(long id) {
        return null;
    }

    @Override
    public IngredientDTO save(IngredientDTO ing) {
        return null;
    }

    @Override
    public IngredientDTO update(IngredientDTO ing) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
