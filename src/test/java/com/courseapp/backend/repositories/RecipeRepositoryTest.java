package com.courseapp.backend.repositories;

import com.courseapp.backend.model.recipe.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static com.courseapp.backend.RecipeDataFactory.createRecipesCollection;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
class RecipeRepositoryTest  {

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    void testFindAll() {
        // given
        int expectedListSize = 2;
        // when
        when(recipeRepository.findAll()).thenReturn(createRecipesCollection());
        List<Recipe> list = recipeRepository.findAll();
        // then
        assertEquals(expectedListSize, list.size());
        assertNotNull(list.get(0).getIngredients());
        assertNotNull(list.get(1).getIngredients());
    }


}