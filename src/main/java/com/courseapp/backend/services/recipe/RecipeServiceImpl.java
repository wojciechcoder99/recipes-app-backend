package com.courseapp.backend.services.recipe;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.model.recipe.RecipeDTO;
import com.courseapp.backend.repositories.IGenericRepository;
import com.courseapp.backend.repositories.RecipeRepository;
import com.courseapp.backend.services.BaseService;
import com.courseapp.backend.services.BaseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class RecipeServiceImpl extends BaseServiceImpl<Recipe, RecipeDTO> {

    private final RecipeRepository recipeRepository;

    private final BaseService<IngredientDTO, Ingredient> ingredientService;

    public RecipeServiceImpl(ModelMapper modelMapper, RecipeRepository recipeRepository, BaseService<IngredientDTO, Ingredient> ingredientService) {
        super(modelMapper);
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    protected IGenericRepository<Recipe, Long> getRepositoryInstance() {
        return recipeRepository;
    }

    @Override
    protected boolean isEntityExistsAndMatchId(long id, RecipeDTO dto) {
        return recipeRepository.existsById(id) && dto.getId() == id;
    }

    @Override
    public Optional<RecipeDTO> save(RecipeDTO dto) {
        Recipe entity = null;
        entity = convertToEntity(dto);
        entity = recipeRepository.save(entity);
        return Optional.of(convertToDTO(entity));
    }

    @Override
    public Recipe convertToEntity(RecipeDTO recipeDTO) {
        List<Ingredient> result = (List<Ingredient>) ingredientService.convertToCollectionOfEntities(recipeDTO.getIngredients());
        Recipe converted = modelMapper.map(recipeDTO, Recipe.class);
        converted.setIngredients(Set.copyOf(result));
        return converted;
    }

    @Override
    public RecipeDTO convertToDTO(Recipe recipe) {
        Iterable<IngredientDTO> result = ingredientService.convertToCollectionOfDTOs(recipe.getIngredients());
        RecipeDTO converted = modelMapper.map(recipe, RecipeDTO.class);
        converted.setIngredients(result);
        return converted;
    }

    @Override
    public Iterable<Recipe> convertToCollectionOfEntities(Iterable<RecipeDTO> dtosCollection) {
        return StreamSupport.stream(dtosCollection.spliterator(), false)
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<RecipeDTO> convertToCollectionOfDTOs(Iterable<Recipe> entitiesCollection) {
        return StreamSupport.stream(entitiesCollection.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
