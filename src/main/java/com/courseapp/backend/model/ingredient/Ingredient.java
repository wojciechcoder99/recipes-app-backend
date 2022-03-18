package com.courseapp.backend.model.ingredient;

import com.courseapp.backend.model.NotifyAboutChanges;

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
public class Ingredient implements NotifyAboutChanges {
        //TODO: To finish association between entities
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long id;

        @NotNull
        String name;

        @NotNull
        int amount;

        @ManyToOne
        @JoinColumn(name = "id")
        private Recipe recipe;

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

        @PostRemove
        @Override
        public void afterRemove() {
                NotifyAboutChanges.super.afterRemove();
        }

        @Override
        public void afterUpdate() {
                NotifyAboutChanges.super.afterUpdate();
        }

}
