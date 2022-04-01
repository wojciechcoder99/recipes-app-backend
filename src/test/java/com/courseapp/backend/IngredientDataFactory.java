package com.courseapp.backend;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;

import java.util.Arrays;
import java.util.List;

public final class IngredientDataFactory {
    public static List<Ingredient> createIngredientList() {
        return Arrays.asList(new Ingredient(1, "Mango",2));
    }

    public static Ingredient createIngredient(long id) {
        return new Ingredient(id, "Apple", 3);
    }
    public static IngredientDTO createIngredientDTO(long id) {
        return new IngredientDTO(id, "Apple", 3);
    }
}
