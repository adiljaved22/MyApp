package com.example.myapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipe-table")
data class RecipeEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uri: String,
    val name: String,
    val description: String


)
