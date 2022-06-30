package com.example.recipes.model

import android.os.Parcelable
import com.example.recipes.room.RecipeEntity
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Recipe(
    val id: Int,
    val cookingMinutes: Int,
    val extendedIngredients: @RawValue List<ExtendedIngredient>,
    val image: String,
    val instructions: String,
    val preparationMinutes: Int,
    val title: String,
): Parcelable{

    fun toEntity(): RecipeEntity{
        return RecipeEntity(
            id,
            cookingMinutes,
            extendedIngredients.map { it.original },
            image,
            instructions,
            preparationMinutes,
            title
        )
    }

    companion object{
        fun fromEntity(entity: RecipeEntity): Recipe{
            with(entity){
                return Recipe(
                    id,
                    cookingMinutes,
                    ingredients.map { ExtendedIngredient(it) },
                    image,
                    instructions,
                    preparationMinutes,
                    title
                )
            }
        }
    }

}