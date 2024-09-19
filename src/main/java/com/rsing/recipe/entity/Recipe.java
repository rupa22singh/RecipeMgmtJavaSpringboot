package com.rsing.recipe.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recipes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "recipe_type")
    private RECIPE_TYPE type;

    @Column(name = "creation_date",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;


    // default date format saved: yyyy-MM-dd'T'HH:mm:ss
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "last_updated_time",nullable = false)
    private LocalDateTime lastUpdatedAt;

    @Column(nullable = false)
    private int servings;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients",joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String instructions;

    @PrePersist
    protected void onCreate(){
        creationDate = LocalDateTime.now();
        lastUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        lastUpdatedAt = LocalDateTime.now();
    }

    public enum RECIPE_TYPE{
        VEG,
        NONVEG
    }
}
