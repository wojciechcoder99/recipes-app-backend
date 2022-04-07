package com.courseapp.backend.repositories;

import com.courseapp.backend.model.recipe.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends IGenericRepository<Recipe, Long> {
    @Override
    @Query("from Recipe r left join fetch r.ingredients")
    List<Recipe> findAll();

    @Override
    @Query(value = "SELECT * FROM recipes LEFT JOIN ingredients ON recipes.recipe_id = ingredients.recipe_id", nativeQuery = true)
    Page<Recipe> findAll(Pageable pageable);
}
