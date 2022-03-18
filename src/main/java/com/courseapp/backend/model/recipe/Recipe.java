package com.courseapp.backend.model.recipe;

import com.courseapp.backend.model.NotifyAboutChanges;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
