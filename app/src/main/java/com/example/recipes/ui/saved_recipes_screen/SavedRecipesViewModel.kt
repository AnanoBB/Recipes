package com.example.recipes.ui.saved_recipes_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipes.model.Recipe
import com.example.recipes.network.RetrofitClient
import com.example.recipes.repository.RecipeRepository
import com.example.recipes.room.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedRecipesViewModel(private val app: Application): AndroidViewModel(app) {
    private val repository = RecipeRepository(RetrofitClient.getRecipeApi(), RecipeDatabase.getDatabase(app).recipeDao())

    val recipes = MutableLiveData<List<Recipe>>()

    fun getSavedRecipes(){
        viewModelScope.launch(Dispatchers.IO) {
            val entities = repository.getSavedRecipes()
            recipes.postValue(entities.map { Recipe.fromEntity(it) })
        }
    }

}