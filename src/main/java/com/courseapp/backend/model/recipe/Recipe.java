package com.courseapp.backend.model.recipe;

import com.courseapp.backend.model.NotifyAboutChanges;
import com.courseapp.backend.model.ingredient.Ingredient;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipe implements NotifyAboutChanges {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;

    @NotBlank
    private String description;
    private String imagePath;

    @OneToMany(mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    @PostLoad
    @Override
    public void afterLoad() {
        NotifyAboutChanges.super.afterLoad();
    }

    @PostPersist
    @Override
    public void afterSave() {
        NotifyAboutChanges.super.afterSave();
    }

    @PostUpdate
    @Override
    public void afterUpdate() {
        NotifyAboutChanges.super.afterUpdate();
    }

    @PostRemove
    @Override
    public void afterRemove() {
        NotifyAboutChanges.super.afterRemove();
    }
}
