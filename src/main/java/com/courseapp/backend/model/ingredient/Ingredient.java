package com.courseapp.backend.model.ingredient;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long id;
        @NotNull
        String name;
        @NotNull
        int amount;
}
