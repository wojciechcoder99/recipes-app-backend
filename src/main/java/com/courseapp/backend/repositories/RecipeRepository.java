package com.courseapp.backend.repositories;

import com.courseapp.backend.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends IGenericRepository<Recipe, Long> {
    @Override
    @Query("from Recipe r left join fetch r.ingredients")
    List<Recipe> findAll();
}
