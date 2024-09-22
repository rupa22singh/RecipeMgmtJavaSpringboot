package com.rsing.recipe.controller;

import com.rsing.recipe.payload.DeleteResponse;
import com.rsing.recipe.payload.RecipeDto;
import com.rsing.recipe.payload.RecipeResponse;
import com.rsing.recipe.service.RecipeService;
import com.rsing.recipe.utils.AppsConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping
    @Operation(summary = "Returns recipes pagewise")
    @ApiResponse(description = "All the recipes data based on the params given in the request",responseCode = "200")
    public ResponseEntity<RecipeResponse> getRecipes(
            @RequestParam(value = "pageNo",defaultValue = AppsConstant.DEFAULT_PAGE_NO,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppsConstant.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppsConstant.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppsConstant.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return ResponseEntity.ok(recipeService.getRecipes(pageNo,pageSize,sortBy,sortDir));
    }

    @GetMapping("/allrecipes")
    public ResponseEntity<List<RecipeDto>> getAllRecipes(){
        return ResponseEntity.ok(recipeService.getRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable("id")long recipeId){
        return ResponseEntity.ok(recipeService.getRecipe(recipeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable("id") long recipeId,@Valid @RequestBody RecipeDto recipeDto){
        return ResponseEntity.ok(recipeService.updateRecipe(recipeId,recipeDto));
    }

    @PostMapping
    public ResponseEntity<RecipeDto> addRecipe(@Valid @RequestBody RecipeDto recipeDto){
        return new ResponseEntity<>(recipeService.addRecipe(recipeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteRecipe(@PathVariable("id")long recipeId){
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.noContent().build();
    }


}
