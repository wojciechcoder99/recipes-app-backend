package com.courseapp.backend.model.ingredient;

import lombok.Data;

@Data
public class IngredientDTO {
    long id;
    String name;
    int amount;
}
