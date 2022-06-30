package com.example.recipes.ui.recipe_details_screen

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

class RecipeDetailsViewModel(private val app: Application): AndroidViewModel(app) {
    private val repository = RecipeRepository(RetrofitClient.getRecipeApi(), RecipeDatabase.getDatabase(app).recipeDao())

    val recipeSaved = MutableLiveData<Boolean>()

    fun saveRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveRecipe(recipe.toEntity())
        }
    }

    fun checkRecipeSaved(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            recipeSaved.postValue(repository.checkRecipeSaved(id))
        }
    }

    fun removeRecipe(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeRecipe(id)
        }
    }
}