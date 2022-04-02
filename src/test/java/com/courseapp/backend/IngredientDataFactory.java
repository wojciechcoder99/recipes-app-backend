package com.courseapp.backend;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import java.util.Collections;

public final class IngredientDataFactory {
    private static final Ingredient testingIngredient = new Ingredient(1, "Apple", 3);
    private static final IngredientDTO testingIngredientDTO = new IngredientDTO(1, "Apple", 3);

    public static Ingredient createIngredient() {
        return testingIngredient;
    }
    public static IngredientDTO createIngredientDTO() {
        return testingIngredientDTO;
    }

    public static Iterable<IngredientDTO> createIngredientDTOsCollection() {
        return Collections.singletonList(createIngredientDTO());
    }
    public static Iterable<Ingredient> createIngredientsCollection() {
        return Collections.singletonList(createIngredient());
    }
}
