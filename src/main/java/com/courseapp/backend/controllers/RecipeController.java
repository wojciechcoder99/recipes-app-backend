package com.courseapp.backend.controllers;

import com.courseapp.backend.annotations.AllowExceptionHandler;
import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.model.recipe.RecipeDTO;
import com.courseapp.backend.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("api/recipes")
@Validated
@AllowExceptionHandler
public class RecipeController {
    @Autowired
    private BaseService<RecipeDTO, Recipe> recipeService;

    @GetMapping
    public ResponseEntity<Iterable<RecipeDTO>> getALlRecipes() {
        List<RecipeDTO> recipeDTOs = (List<RecipeDTO>) recipeService.findAll();
        return fetchRecipes((List<RecipeDTO>) recipeService.findAll());
    }

    @GetMapping(params = "page")
    public ResponseEntity<Iterable<RecipeDTO>> getALlRecipes(@Param("page") int page) {
        return fetchRecipes((List<RecipeDTO>) recipeService.findAll(Pageable.ofSize(page)));
    }

    private ResponseEntity<Iterable<RecipeDTO>> fetchRecipes(List<RecipeDTO> recipeDTOs) {
        recipeDTOs = recipeDTOs.stream()
                .map(this::addLinkToSingleObject)
                .map(this::addLinkToCollection)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(recipeDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@Min(1) @PathVariable long id) {
        return recipeService.findById(id)
                .map(this::addLinkToSingleObject)
                .map(this::addLinkToCollection)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody RecipeDTO recipe) {
       return  recipeService.save(Optional.ofNullable(recipe))
                .map(this::addLinkToSingleObject)
                .map(this::addLinkToCollection)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(@Min(1) @PathVariable long id, @Valid @RequestBody RecipeDTO recipeDTO) {
        return recipeService.update(id, Optional.ofNullable(recipeDTO))
                .map(this::addLinkToSingleObject)
                .map(this::addLinkToCollection)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@Min(1) @PathVariable long id) {
        return recipeService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    private RecipeDTO addLinkToSingleObject(RecipeDTO recipeDTO) {
        return recipeDTO.add(linkTo(RecipeDTO.class)
                .slash("api")
                .slash("recipes")
                .slash(recipeDTO.getId())
                .withSelfRel());
    }

    private RecipeDTO addLinkToCollection(RecipeDTO recipeDTO) {
        return recipeDTO.add(linkTo(RecipeDTO.class)
                .slash("api")
                .slash("recipes")
                .withRel("recipes"));
    }
}
