package com.rsing.recipe.srvice.impl;

import com.rsing.recipe.entity.Recipe;
import com.rsing.recipe.exception.ResourceNotFoundException;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("Test Recipe");
        recipe.setType(Recipe.RECIPE_TYPE.VEG);
        recipe.setIngredients(Arrays.asList("Ingredient 1", "Ingredient 2"));
        recipe.setServings(4);
        recipe.setInstructions("Test instructions");

        recipeDto = new RecipeDto();
        recipeDto.setId(1);
        recipeDto.setName("Test Recipe");
        recipeDto.setType("VEG");
        recipeDto.setIngredients(Arrays.asList("Ingredient 1", "Ingredient 2"));
        recipeDto.setServings(4);
        recipeDto.setInstructions("Test instructions");

        when(modelMapper.map(recipe, RecipeDto.class)).thenReturn(recipeDto);
        when(modelMapper.map(recipeDto, Recipe.class)).thenReturn(recipe);
        when(recipeServiceImpl.addRecipe(recipeDto)).thenReturn(recipeDto);
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(modelMapper.map(recipe, RecipeDto.class)).thenReturn(recipeDto);
        recipeDto = modelMapper.map(recipe, RecipeDto.class);

    }


    @Test
    void testAddRecipe() {
        RecipeDto addedRecipe = recipeServiceImpl.addRecipe(recipeDto);
        assertThat(addedRecipe).isNotNull();
        assertThat(addedRecipe.getName()).isEqualTo(recipeDto.getName());
    }

    @Test
    void testUpdateRecipe() {
        RecipeDto updatedRecipeDto = new RecipeDto();
        updatedRecipeDto.setId(1);
        updatedRecipeDto.setName("Updated Recipe");
        updatedRecipeDto.setType("NONVEG");
        updatedRecipeDto.setIngredients(Arrays.asList("Ingredient 0", "Ingredient 3"));
        updatedRecipeDto.setServings(4);
        updatedRecipeDto.setInstructions("Updated instructions");

        when(recipeRepository.findById(recipe.getId())).thenReturn(Optional.of(recipe));
        when(recipeServiceImpl.updateRecipe(recipe.getId(), updatedRecipeDto)).thenReturn(updatedRecipeDto);
        RecipeDto updatedRecipe = recipeServiceImpl.updateRecipe(recipe.getId(), updatedRecipeDto);
        assertThat(updatedRecipe.getName()).isEqualTo(updatedRecipeDto.getName());
    }

    @Test
    void testGetRecipes() {
        // Arrange
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe1.setName("Recipe 1");

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipe2.setName("Recipe 2");

        RecipeDto recipeDto1 = new RecipeDto();
        recipeDto1.setId(1L);
        recipeDto1.setName("Recipe 1");

        RecipeDto recipeDto2 = new RecipeDto();
        recipeDto2.setId(2L);
        recipeDto2.setName("Recipe 2");

        List<Recipe> recipeList = Arrays.asList(recipe1, recipe2);

        when(recipeRepository.findAll()).thenReturn(recipeList);
        when(modelMapper.map(recipe1, RecipeDto.class)).thenReturn(recipeDto1);
        when(modelMapper.map(recipe2, RecipeDto.class)).thenReturn(recipeDto2);

        // Act
        List<RecipeDto> result = recipeServiceImpl.getRecipes();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Recipe 1", result.get(0).getName());
        assertEquals("Recipe 2", result.get(1).getName());

        verify(recipeRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(recipe1, RecipeDto.class);
        verify(modelMapper, times(1)).map(recipe2, RecipeDto.class);
    }

    @Test
    void testGetRecipe_Success() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        RecipeDto result = recipeServiceImpl.getRecipe(1L);
        assertNotNull(result);
        assertEquals(recipe.getName(), result.getName());
        assertEquals(recipe.getType().name(), result.getType());
    }

    @Test
    void testGetRecipe_NotFound() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            recipeServiceImpl.getRecipe(1L);
        });
    }
}
