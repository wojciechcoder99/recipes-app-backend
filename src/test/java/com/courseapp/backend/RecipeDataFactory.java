package com.courseapp.backend;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.model.recipe.RecipeDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public final class RecipeDataFactory {
    private static final Recipe testingRecipe = new Recipe(1, "Burger", "The best", "text",
            new HashSet<>(Arrays.asList(new Ingredient(1, "Apple", 5),
                    new Ingredient(2, "Mango", 6))));

    private static final RecipeDTO testingRecipeDTO = new RecipeDTO(1, "Burger", "The best", "text",
            new HashSet<>(Arrays.asList(new IngredientDTO(1, "Apple", 5),
                    new IngredientDTO(2, "Mango", 6))));

    public static List<Recipe> createRecipesCollection() {
        return Arrays.asList(new Recipe(1, "Burger", "The best", "text",
                        new HashSet<>(Arrays.asList(new Ingredient(1, "Apple", 5),
                                new Ingredient(2, "Mango", 6)))),
                new Recipe(2, "Pastuch", "Des", "text",
                        new HashSet<>(Collections.singletonList(new Ingredient(1, "Apple", 4)))));
    }
    public static List<RecipeDTO> createRecipeDTOsCollection() {
        return Arrays.asList(new RecipeDTO(1, "Burger", "The best", "text",
                        new HashSet<>(Arrays.asList(new IngredientDTO(1, "Apple", 5),
                                new IngredientDTO(2, "Mango", 6)))),
                new RecipeDTO(2, "Pastuch", "Des", "text",
                        new HashSet<>(Collections.singletonList(new IngredientDTO(1, "Apple", 4)))));
    }

    public static Recipe createRecipe() {
        return testingRecipe;
    }

    public static RecipeDTO createRecipeDTO(long id) {
        return testingRecipeDTO;
    }

    public static Iterable<Recipe> createCollectionOfRecipe() {
        return Collections.singletonList(testingRecipe);
    }
    public static Iterable<RecipeDTO> createCollectionOfRecipeDTOs() {
        return Collections.singletonList(testingRecipeDTO);
    }
}
