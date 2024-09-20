package com.rsing.recipe.srvice.impl;


import com.rsing.recipe.entity.Recipe;
import com.rsing.recipe.payload.RecipeDto;
import com.rsing.recipe.repository.RecipeRepository;
import com.rsing.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.is;


@SpringBootTest
@AutoConfigureMockMvc
public class RecipeServiceImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RecipeService recipeService;

    private Recipe recipe;
    private RecipeDto recipeDto;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setName("Test Recipe");
        recipe.setType(Recipe.RECIPE_TYPE.VEG);
        recipe.setIngredients(List.of("Ingredient 1", "Ingredient 2"));
        recipe.setServings(4);
        recipe.setInstructions("Test Instructions");

        recipeRepository.save(recipe);

        recipeDto = modelMapper.map(recipe, RecipeDto.class);
    }

    @Test
    void testGetRecipe_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/recipes/" + recipe.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Test Recipe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", is("VEG")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.servings", is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructions", is("Test Instructions")));
    }

    @Test
    void testAddRecipe_Success() throws Exception {
        String recipeJson = """
        {
          "name": "New Recipe",
          "type": "VEG",
          "ingredients": ["Ingredient 1", "Ingredient 2"],
          "servings": 2,
          "instructions": "Cook and serve."
        }
        """;

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/recipes")
                        .content(recipeJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("New Recipe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", is("VEG")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.servings", is(2)));
    }

    @Test
    void testGetRecipe_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/recipes/9999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
