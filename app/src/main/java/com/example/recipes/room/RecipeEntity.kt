package com.example.recipes.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey
    val id: Int,
    val cookingMinutes: Int,
    val ingredients: List<String>,
    val image: String,
    val instructions: String,
    val preparationMinutes: Int,
    val title: String,
)