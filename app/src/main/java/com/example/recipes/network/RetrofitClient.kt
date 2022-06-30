package com.example.recipes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.spoonacular.com/"

    private var API: RecipeApi? = null
    private val CLIENT = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRecipeApi(): RecipeApi {
        if (API == null) {
            synchronized(this) {
                API = CLIENT.create(RecipeApi::class.java)
            }
        }
        return API!!
    }

}