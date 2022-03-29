package com.courseapp.backend;

import com.courseapp.backend.model.recipe.Recipe;
import com.courseapp.backend.model.recipe.RecipeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		TypeMap<Recipe, RecipeDTO> propertyMapper = modelMapper.createTypeMap(Recipe.class, RecipeDTO.class);
		propertyMapper.addMappings(mapper -> mapper.skip(RecipeDTO::setIngredients));
		TypeMap<RecipeDTO, Recipe> propertyMapper2 = modelMapper.createTypeMap(RecipeDTO.class, Recipe.class);
		propertyMapper2.addMappings(mapper -> mapper.skip(Recipe::setIngredients));
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
