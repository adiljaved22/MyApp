package com.example.myapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.RecipeEntity
import com.example.myapp.data.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
class ViewModel(private val repository: RecipeRepository =Graph.recipeRepository): ViewModel() {

    lateinit var  getallRecipes:Flow<List<RecipeEntity>>

    init {

        getallRecipes   = repository.getAllRecipes()

    }

    fun add(new: RecipeEntity){

        viewModelScope.launch(Dispatchers.IO) {

            repository.addAllRecipes(entity = new)

        }

    }

}