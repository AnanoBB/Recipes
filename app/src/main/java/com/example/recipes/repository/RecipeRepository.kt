package com.example.recipes.repository

import com.example.recipes.model.Recipe
import com.example.recipes.network.RecipeApi
import com.example.recipes.room.RecipeDao
import com.example.recipes.room.RecipeEntity

class RecipeRepository(private val api: RecipeApi, private val dao: RecipeDao) {

    suspend fun getRandomRecipes(recipeCount: Int) = api.getRandomRecipes(recipeCount)

    suspend fun saveRecipe(recipe: RecipeEntity) = dao.saveRecipe(recipe)

    suspend fun getSavedRecipes(): List<RecipeEntity> = dao.getSavedRecipes()

    suspend fun checkRecipeSaved(id: Int): Boolean = dao.checkRecipeIsSaved(id)

    suspend fun removeRecipe(id:  Int) = dao.removeRecipe(id)
}