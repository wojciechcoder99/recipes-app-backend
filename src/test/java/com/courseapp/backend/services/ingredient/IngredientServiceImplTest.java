package com.courseapp.backend.services.ingredient;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.repositories.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.courseapp.backend.IngredientDataFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
class IngredientServiceImplTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    void setUp() {
        ingredientService = new IngredientServiceImpl(modelMapper, ingredientRepository);
    }

    @Test
    void testConvertToEntity() {
        // given
        IngredientDTO toConvert = createIngredientDTO();
        Ingredient expected = createIngredient();
        when(modelMapper.map(toConvert, Ingredient.class)).thenReturn(expected);
        // when
        Ingredient actual = ingredientService.convertToEntity(toConvert);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void testConvertToDTO() {
        // given
        Ingredient toConvert = createIngredient();
        IngredientDTO expected = createIngredientDTO();
        when(modelMapper.map(toConvert, IngredientDTO.class)).thenReturn(expected);
        // when
        IngredientDTO actual = ingredientService.convertToDTO(toConvert);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void testConvertToCollectionOfEntities() {
        // given
        Iterable<IngredientDTO> toConvert = createCollectionOfIngredientDTOs();
        Iterable<Ingredient> expected = createCollectionOfIngredients();
        when(ingredientService.convertToEntity(createIngredientDTO())).thenReturn(createIngredient());
        // when
        Iterable<Ingredient> actual = ingredientService.convertToCollectionOfEntities(toConvert);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void testConvertToCollectionOfDTOs() {
        // given
        Iterable<Ingredient> toConvert = createCollectionOfIngredients();
        Iterable<IngredientDTO> expected = createCollectionOfIngredientDTOs();
        when(ingredientService.convertToDTO(createIngredient())).thenReturn(createIngredientDTO());
        // when
        Iterable<IngredientDTO> actual = ingredientService.convertToCollectionOfDTOs(toConvert);
        // then
        assertEquals(expected, actual);
    }
}