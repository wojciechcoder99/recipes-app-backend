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
public class Recipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name ="recipe_id")
    private long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;

    @NotBlank
    private String description;
    private String imagePath;

    @OneToMany(mappedBy = "recipe")
    @Cascade({CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Ingredient> ingredients;
}
