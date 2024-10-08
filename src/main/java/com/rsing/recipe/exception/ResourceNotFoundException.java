package com.rsing.recipe.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


    public ResourceNotFoundException(String recipeName, long recipeId) {
        super(String.format("%s's ID does not match with given %s ID", recipeName, recipeId));
        this.resourceName = recipeName;
        fieldName = "Recipe ID";
        fieldValue = recipeId;
    }

}

