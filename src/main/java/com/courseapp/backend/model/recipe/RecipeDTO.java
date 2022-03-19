package com.courseapp.backend.model.recipe;

import com.courseapp.backend.model.ingredient.IngredientDTO;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class RecipeDTO extends RepresentationModel<RecipeDTO> {
    long id;
    String name;
    String description;
    String imagePath;
    List<IngredientDTO> ingredients;
}
