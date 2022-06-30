package com.example.recipes.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class IngredientsListTypeConverter {

    @TypeConverter
    fun listToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

}