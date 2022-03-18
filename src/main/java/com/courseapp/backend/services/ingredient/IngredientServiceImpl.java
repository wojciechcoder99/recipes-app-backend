package com.courseapp.backend.services.ingredient;

import com.courseapp.backend.model.ingredient.Ingredient;
import com.courseapp.backend.model.ingredient.IngredientDTO;
import com.courseapp.backend.repositories.IngredientRepository;
import com.courseapp.backend.services.ConvertToEntityOrDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService,
        ConvertToEntityOrDto<Ingredient, IngredientDTO> {

    //TODO: Add liquibase
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<IngredientDTO> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<IngredientDTO> findById(long id) {
        return ingredientRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public Optional<IngredientDTO> save(Optional<IngredientDTO> ing) {
        return ing.map(i -> convertToDTO(ingredientRepository.save(convertToEntity(i))));
    }

    @Override
    public Optional<IngredientDTO> update(long id, Optional<IngredientDTO> ing) {
        if (ing.isPresent() &&
                ingredientRepository.existsById(ing.get().getId()) &&
                ing.get().getId() == id) {
            return Optional.of(convertToDTO(
                    ingredientRepository.save(convertToEntity(ing.get()))));
        }
        return Optional.empty();
    }

    @Override
    public Optional<IngredientDTO> deleteById(long id) {
        if (ingredientRepository.existsById(id)) {
            IngredientDTO deleted = convertToDTO(ingredientRepository.findById(id).get());
            ingredientRepository.deleteById(id);
            return Optional.of(deleted);
        }
        return Optional.empty();
    }

    @Override
    public Ingredient convertToEntity(IngredientDTO dto) {
        return modelMapper.map(dto, Ingredient.class);
    }

    @Override
    public IngredientDTO convertToDTO(Ingredient entity) {
        return modelMapper.map(entity, IngredientDTO.class);
    }
}
