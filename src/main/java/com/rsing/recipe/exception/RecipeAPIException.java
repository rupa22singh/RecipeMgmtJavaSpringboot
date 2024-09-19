package com.rsing.recipe.exception;

import org.springframework.http.HttpStatus;

public class RecipeAPIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public RecipeAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
