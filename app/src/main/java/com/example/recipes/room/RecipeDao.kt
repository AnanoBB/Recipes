package com.example.recipes.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipes.model.Recipe

@Dao
interface RecipeDao {

    @Insert
    fun saveRecipe(recipe: RecipeEntity)

    @Query("select * from recipe")
    fun getSavedRecipes(): List<RecipeEntity>

    @Query("SELECT EXISTS(SELECT * FROM recipe WHERE id=:id)")
    fun checkRecipeIsSaved(id: Int): Boolean

    @Query("delete from recipe where id=:id")
    fun removeRecipe(id: Int)
}