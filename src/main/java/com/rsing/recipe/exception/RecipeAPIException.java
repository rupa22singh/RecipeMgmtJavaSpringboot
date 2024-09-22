package com.rsing.recipe.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class RecipeAPIException extends RuntimeException{
    @Getter
    private final HttpStatus httpStatus;
    private final String message;

    public RecipeAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
