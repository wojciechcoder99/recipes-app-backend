package com.courseapp.backend.model.ingredient;

import com.courseapp.backend.model.recipe.RecipeDTO;
import lombok.Data;

@Data
public class IngredientDTO {
    long id;
    String name;
    int amount;
    RecipeDTO recipe;
}
