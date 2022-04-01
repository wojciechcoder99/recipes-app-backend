package com.courseapp.backend;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.model.recipe.RecipeDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public final class RecipeDataFactory {
    public static List<Recipe> initRecipesList() {
        return Arrays.asList(new Recipe(1, "Burger", "The best", "text",
                        new HashSet<>(Arrays.asList(new Ingredient(1, "Apple", 5),
                                new Ingredient(2, "Mango", 6)))),
                new Recipe(2, "Pastuch", "Des", "text",
                        new HashSet<>(Arrays.asList(new Ingredient(1, "Apple", 4)))));
    }

    public static Recipe createRecipe(long id) {
        return new Recipe(id, "Pastuch", "Des", "text",
                new HashSet<Ingredient>(Arrays.asList(new Ingredient(1, "Apple", 4))));
    }

    public static Ingredient createIngredient(long id) {
        return new Ingredient(id, "Mango", 2);
    }

    public static RecipeDTO createRecipeDTO(long id) {
        return new RecipeDTO(id, "Pastuch", "Des", "text",
                new HashSet<IngredientDTO>(Arrays.asList(new IngredientDTO(1, "Apple", 4))));
    }

    public static IngredientDTO createIngredientDTO(long id) {
        return new IngredientDTO(id, "Mango", 2);
    }
}
