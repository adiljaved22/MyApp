package com.example.myapp

import android.content.Context
import androidx.room.Room
import com.example.myapp.data.RecipeDatabase
import com.example.myapp.data.RecipeRepository

object Graph {
    lateinit var database: RecipeDatabase
    val recipeRepository by lazy {
        RecipeRepository(newDao = database.dao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            name = "Recipelist.db"
        ).fallbackToDestructiveMigration().build()

    }
}