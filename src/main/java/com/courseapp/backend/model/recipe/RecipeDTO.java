package com.courseapp.backend.model.recipe;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class RecipeDTO extends RepresentationModel<RecipeDTO> {
    long id;
    String name;
    String description;
    String imagePath;
}
