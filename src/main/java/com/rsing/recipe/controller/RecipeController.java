package com.rsing.recipe.controller;

import com.rsing.recipe.payload.DeleteResponse;
import com.rsing.recipe.payload.RecipeDto;
import com.rsing.recipe.payload.RecipeResponse;
import com.rsing.recipe.service.RecipeService;
import com.rsing.recipe.utils.AppsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<RecipeResponse> getRecipes(
            @RequestParam(value = "pageNo",defaultValue = AppsConstant.DEFAULT_PAGE_NO,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppsConstant.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppsConstant.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppsConstant.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return ResponseEntity.ok(recipeService.getRecipes(pageNo,pageSize,sortBy,sortDir));
    }

    @GetMapping("/allrecipes")
    public ResponseEntity<List<RecipeDto>> getRecipes(){
        return ResponseEntity.ok(recipeService.getRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable("id")long recipeId){
        return ResponseEntity.ok(recipeService.getRecipe(recipeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable("id") long recipeId, @RequestBody RecipeDto recipeDto){
        return ResponseEntity.ok(recipeService.updateRecipe(recipeId,recipeDto));
    }

    @PostMapping
    public ResponseEntity<RecipeDto> addRecipe(@RequestBody RecipeDto recipeDto){
        return new ResponseEntity<>(recipeService.addRecipe(recipeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteRecipe(@PathVariable("id")long recipeId){
        recipeService.deleteRecipe(recipeId);
        DeleteResponse deleteResponse = new DeleteResponse("Recipe deleted successfully.");
        return ResponseEntity.ok(deleteResponse);
    }


}
