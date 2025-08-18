package com.example.myapp.data

import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val newDao: RecipeDao) {
    suspend fun addAllRecipes(entity: RecipeEntity){
        newDao.addAllRecipe(entity)
    }
    fun getAllRecipes(): Flow<List<RecipeEntity>>{
        return newDao.getAllRecipes()
    }
}