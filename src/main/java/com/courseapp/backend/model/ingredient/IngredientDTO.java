package com.courseapp.backend.model.ingredient;

import com.courseapp.backend.model.GenericDTO;
import com.courseapp.backend.model.recipe.RecipeDTO;
import lombok.AllArgsConstructor;
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

    public IngredientDTO(long id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
