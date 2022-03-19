package com.courseapp.backend.model.ingredient;

import com.courseapp.backend.model.BaseEntity;

import com.courseapp.backend.model.recipe.Recipe;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name ="ing_id")
        long id;

        @NotNull
        String name;

        @NotNull
        int amount;

        @ManyToOne
        @JoinColumn(name = "recipe_id")
        private Recipe recipe;

        @Embedded
        private BaseEntity baseEntity = new BaseEntity();

}
