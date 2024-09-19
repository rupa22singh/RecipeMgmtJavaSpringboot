package com.rsing.recipe.service;

import com.rsing.recipe.payload.RecipeDto;
import com.rsing.recipe.payload.RecipeResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RecipeService {

    List<RecipeDto> getRecipes();

    RecipeResponse getRecipes(int pageNo, int pageSize, String sortBy, String sortDir);

    RecipeDto addRecipe(RecipeDto recipe);

    RecipeDto getRecipe(long id);

    RecipeDto updateRecipe(long id,RecipeDto recipeDto);

    void deleteRecipe(long id);
}
