package com.courseapp.backend.model.ingredient;

import com.courseapp.backend.model.BaseEntity;
import com.courseapp.backend.model.NotifyAboutChanges;

import com.courseapp.backend.model.recipe.Recipe;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ingredient extends BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name ="ing_id")
        long id;

        @NotNull
        String name;

        @NotNull
        int amount;

        @ManyToOne
        @Cascade({CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
        @JoinColumn(name = "recipe_id")
        private Recipe recipe;

}
