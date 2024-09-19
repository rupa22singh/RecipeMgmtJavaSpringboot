package com.rsing.recipe.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rsing.recipe.entity.Recipe;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private long id;

    @NotEmpty(message = "Name should not be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime lastUpdatedAt;

    @NotNull(message = "Recipe type cannot be null")
    @Pattern(regexp = "VEG|NONVEG", message = "Invalid recipe type")
    private String type;

    private int servings;

    private List<String> ingredients;

    @NotEmpty
    private String instructions;
}
