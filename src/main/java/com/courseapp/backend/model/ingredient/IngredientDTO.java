package com.courseapp.backend.model.ingredient;

import com.courseapp.backend.model.GenericDTO;
import com.courseapp.backend.model.recipe.RecipeDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class IngredientDTO extends RepresentationModel<IngredientDTO> implements GenericDTO {
    long id;
    String name;
    int amount;

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
