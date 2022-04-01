package com.courseapp.backend.repositories;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.services.BaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
class RecipeRepositoryTest  {

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    void testFindAll() {
        // given
        when(recipeRepository.findAll()).thenReturn(initRecipesList());
        int expectedListSize = 2;
        // when
        List<Recipe> list = recipeRepository.findAll();
        // then
        assertEquals(expectedListSize, list.size());
        assertNotNull(list.get(0).getIngredients());
        assertNotNull(list.get(1).getIngredients());
    }

    private List<Recipe> initRecipesList() {
        return Arrays.asList(new Recipe(1, "Burger", "The best", "text",
                new HashSet<>(Arrays.asList(new Ingredient(1, "Apple", 5),
                        new Ingredient(2, "Mango", 6)))),
                new Recipe(2, "Pastuch", "Des", "text",
                        new HashSet<>(Arrays.asList(new Ingredient(1, "Apple", 4)))));
    }
}