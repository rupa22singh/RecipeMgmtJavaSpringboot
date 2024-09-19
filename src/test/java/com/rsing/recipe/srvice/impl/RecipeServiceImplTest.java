package com.rsing.recipe.srvice.impl;

import com.rsing.recipe.entity.Recipe;
import com.rsing.recipe.payload.RecipeDto;
import com.rsing.recipe.repository.RecipeRepository;
import com.rsing.recipe.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    RecipeServiceImpl recipeServiceImpl;

    private Recipe recipe;
    private RecipeDto recipeDto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Test Recipe");
        recipe.setType(Recipe.RECIPE_TYPE.VEG);
        recipe.setIngredients(Arrays.asList("Ingredient 1","Ingredient 2"));
        recipe.setServings(4);
        recipe.setInstructions("Test instructions");

        recipeDto = modelMapper.map(recipe, RecipeDto.class);
    }

    @Test
    void testAddRecipe(){
        RecipeDto addedRecipe = recipeServiceImpl.addRecipe(recipeDto);
        boolean isEqual = addedRecipe.getName().equals(recipeDto.getName());
        assertThat(addedRecipe.getName()).isEqualTo(recipe.getName());
    }

    @Test
    void testUpdateRecipe(){
        RecipeDto updatedRecipeDto = new RecipeDto();
        updatedRecipeDto.setName("Updated Recipe");
        updatedRecipeDto.setType("NONVEG");
        updatedRecipeDto.setIngredients(Arrays.asList("Ingredient 0","Ingredient 3"));
        updatedRecipeDto.setServings(4);
        updatedRecipeDto.setInstructions("Updated instructions");

        RecipeDto updatedRecipe = recipeServiceImpl.updateRecipe(recipe.getId(), updatedRecipeDto);
        assertThat(updatedRecipe.getName()).isEqualTo(updatedRecipeDto.getName());
    }
}
