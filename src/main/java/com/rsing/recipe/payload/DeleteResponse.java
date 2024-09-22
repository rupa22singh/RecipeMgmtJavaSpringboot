package com.rsing.recipe.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor(force = true)
public class DeleteResponse {

    final String message;
}
