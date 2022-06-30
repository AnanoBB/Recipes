package com.example.recipes.ui.online_recipes_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipes.model.Recipe
import com.example.recipes.network.RetrofitClient
import com.example.recipes.repository.RecipeRepository
import com.example.recipes.room.RecipeDatabase
import kotlinx.coroutines.launch

class RandomRecipesViewModel(private val app: Application): AndroidViewModel(app) {
    private val repository = RecipeRepository(RetrofitClient.getRecipeApi(), RecipeDatabase.getDatabase(app).recipeDao())
    val recipes = MutableLiveData<List<Recipe>>()

    init {
        getRandomRecipes()
    }

    fun getRandomRecipes(recipeCount: Int = 5){
        viewModelScope.launch {
            recipes.postValue(repository.getRandomRecipes(recipeCount).recipes)
        }
    }

}