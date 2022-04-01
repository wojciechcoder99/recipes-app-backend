package com.courseapp.backend.services.ingredient;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.repositories.IngredientRepository;
import com.courseapp.backend.services.BaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static com.courseapp.backend.IngredientDataFactory.createIngredient;
import static com.courseapp.backend.IngredientDataFactory.createIngredientDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IngredientServiceImplTest {

    private ModelMapper modelMapper;
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        ingredientService = new IngredientServiceImpl(modelMapper, ingredientRepository);
    }

    @Test
    void testConvertToEntity() {
        // given
        IngredientDTO toConvert = createIngredientDTO(1);
        Ingredient expected = createIngredient(1);
        when(modelMapper.map(toConvert, Ingredient.class)).thenReturn(expected);
        // when
        Ingredient actual = ingredientService.convertToEntity(toConvert);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void testConvertToDTO() {
    }

    @Test
    void testConvertToCollectionOfEntities() {
    }

    @Test
    void testConvertToCollectionOfDTOs() {
    }
}