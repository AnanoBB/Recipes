package com.example.recipes.network

import com.example.recipes.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/random?apiKey=624b3be1fd634b6ea913dd3ad4a1b9fd")
    suspend fun getRandomRecipes(@Query("number") recipeCount: Int): RecipeApiResponse

}