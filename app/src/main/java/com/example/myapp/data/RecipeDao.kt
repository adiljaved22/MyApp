package com.example.myapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
abstract class RecipeDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAllRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM `Recipe-table`")
    abstract fun getAllRecipes(): Flow<List<RecipeEntity>>
}
