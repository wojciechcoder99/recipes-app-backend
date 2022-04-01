package com.courseapp.backend.services;

import com.courseapp.backend.model.recipe.RecipeDTO;
import com.courseapp.backend.repositories.IngredientRepository;
import com.courseapp.backend.repositories.RecipeRepository;
import com.courseapp.backend.services.ingredient.IngredientServiceImpl;
import com.courseapp.backend.services.recipe.RecipeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static com.courseapp.backend.RecipeDataFactory.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
class RecipeServiceImplTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;
    @InjectMocks
    private IngredientServiceImpl ingredientService;
    @InjectMocks
    private ModelMapper modelMapper;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private IngredientRepository ingredientRepository;

    @Test
    public void testFindAll() {
        // given
        int expectedListSize = 2;
        // when
        when(recipeRepository.findAll()).thenReturn(initRecipesList());
        List<RecipeDTO> result = (List<RecipeDTO>) recipeService.findAll();
        // then
        assertEquals(expectedListSize, result.size());
    }

    @Test
    void testFindById() {
    }

    @Test
    void testSave() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testDeleteById() {
    }

    @Test
    void testSaveAll() {
    }

    @Test
    void testGetRepositoryInstance() {
    }

    @Test
    void testIsEntityExistsAndMatchId() {
    }

    @Test
    void testSave1() {
    }

    @Test
    void testConvertToEntity() {
        // given
//        RecipeDTO toConvert = createRecipeDTO(1);
//        Recipe expected = createRecipe(1);
//        ingredientService = mock(IngredientServiceImpl.class);
//        recipeService = new RecipeServiceImpl(modelMapper, recipeRepository, ingredientService);
//        // when
//        Recipe actual = recipeService.convertToEntity(toConvert);
//        //then
//        assertEquals(expected, actual);
    }

    @Test
    void testConvertToDTO() {
    }

    @Test
    void testConvertToCollectionOfEntities() {
        // TODO: finish implementation as well
    }

    @Test
    void testConvertToCollectionOfDTOs() {
    }
}