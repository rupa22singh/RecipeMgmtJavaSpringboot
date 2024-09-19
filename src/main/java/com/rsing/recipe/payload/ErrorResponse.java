package com.rsing.recipe.payload;

import java.util.Date;

public class ErrorResponse {
    private Date timestamp;
    private String description;
    private String message;

    public ErrorResponse(Date timestamp, String description, String message) {
        this.timestamp = timestamp;
        this.description = description;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
