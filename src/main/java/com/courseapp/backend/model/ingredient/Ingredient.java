package com.courseapp.backend.model.ingredient;

import com.courseapp.backend.model.BaseEntity;

import com.courseapp.backend.model.GenericEntity;
import com.courseapp.backend.model.recipe.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Ingredient implements GenericEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name ="ing_id")
        long id;

        @NotNull
        String name;

        @NotNull
        int amount;

        @Embedded
        @JsonIgnore
        private BaseEntity baseEntity = new BaseEntity();

        @Override
        public String toString() {
                return "Ingredient{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", amount=" + amount +
                        '}';
        }
}
