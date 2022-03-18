package com.courseapp.backend.controllers;

import com.courseapp.backend.model.recipe.RecipeDTO;
import com.courseapp.backend.services.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("api/recipes")
@Validated
public class RecipeController {
    //TODO: Add another abstract layer for controllers and services as well
    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<Iterable<RecipeDTO>> getALlRecipes() {
        List<RecipeDTO> recipeDTOs = (List<RecipeDTO>) recipeService.findAll();
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

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e){
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
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
