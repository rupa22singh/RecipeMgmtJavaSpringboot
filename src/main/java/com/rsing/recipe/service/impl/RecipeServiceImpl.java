package com.rsing.recipe.service.impl;

import com.rsing.recipe.entity.Recipe;
import com.rsing.recipe.exception.ResourceNotFoundException;
import com.rsing.recipe.payload.RecipeDto;
import com.rsing.recipe.payload.RecipeResponse;
import com.rsing.recipe.repository.RecipeRepository;
import com.rsing.recipe.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {


    private RecipeRepository recipeRepository;
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);


    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             ModelMapper modelMapper) {

        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
    }

    private RecipeDto mapToDto(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDto.class);
    }

    private Recipe mapToEntity(RecipeDto recipeDto) {
        return modelMapper.map(recipeDto, Recipe.class);
    }

    @Override
    public RecipeDto addRecipe(@Valid @NotNull RecipeDto recipeDto) {
        logger.info("Adding new recipe: {}", recipeDto.getName());

        Recipe recipe = recipeRepository.save(mapToEntity(recipeDto));
        return mapToDto(recipe);
    }

    @Override
    public RecipeDto updateRecipe(long recipeId,@Valid @NotNull RecipeDto recipeDto) {
        if (recipeId != recipeDto.getId()) {
            logger.error("Mismatched recipe ID during update: recipeId {} does not match recipeDto.getId() {}", recipeId, recipeDto.getId());
            throw new IllegalArgumentException("Recipe ID mismatch.");
        }
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        recipe.setName(recipeDto.getName());
        recipe.setType(Recipe.RECIPE_TYPE.valueOf(recipeDto.getType().toUpperCase()));
        recipe.setIngredients(recipeDto.getIngredients());
        recipe.setServings(recipeDto.getServings());
        recipe.setInstructions(recipeDto.getInstructions());

        Recipe savedRecipe = recipeRepository.save(recipe);
        return mapToDto(savedRecipe);
    }

    @Override
    public List<RecipeDto> getRecipes() {
        List<Recipe> allRecipes = recipeRepository.findAll();
        return allRecipes.stream().map(recipe -> modelMapper.map(recipe, RecipeDto.class)).collect(Collectors.toList());
    }

    @Override
    public RecipeResponse getRecipes(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Recipe> all = recipeRepository.findAll(pageable);
        Set<RecipeDto> contents = all.getContent().stream().map(content -> mapToDto(content)).collect(Collectors.toSet());

        RecipeResponse recipeResponse = new RecipeResponse();
        recipeResponse.setContent(contents);
        recipeResponse.setPageNo(all.getNumber());
        recipeResponse.setPageSize(all.getSize());
        recipeResponse.setTotalPages(all.getTotalPages());
        recipeResponse.setTotalElements(all.getTotalElements());
        recipeResponse.setLast(all.isLast());
        return recipeResponse;
    }


    @Override
    public RecipeDto getRecipe(long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipe id", id));
        return mapToDto(recipe);
    }


    @Override
    public void deleteRecipe(long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipe id", id));
        recipeRepository.delete(recipe);

    }
}
