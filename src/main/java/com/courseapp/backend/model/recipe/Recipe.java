package com.courseapp.backend.model.recipe;

import com.courseapp.backend.model.BaseEntity;
import com.courseapp.backend.model.NotifyAboutChanges;
import com.courseapp.backend.model.ingredient.Ingredient;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "recipes")
public class Recipe  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="recipe_id")
    private long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;

    @NotBlank
    private String description;
    private String imagePath;

    @OneToMany(mappedBy = "recipe")
    @Cascade({CascadeType.ALL})
    private Set<Ingredient> ingredients;

    @Embedded
    private BaseEntity baseEntity = new BaseEntity();
}
