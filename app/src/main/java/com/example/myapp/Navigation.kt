package com.example.myapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
@Composable
fun Navigation(viewModel: ViewModel= viewModel())
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstscreen") {
        composable("firstscreen") {
            FirstScreen {name->
                navController.navigate("secondscreen/$name")
            }


        }
        composable("secondscreen/{name}") { backstackentry ->
            val n = backstackentry.arguments?.getString("name") ?: ""
            SecondScreen(n) {
                navController.navigate("thirdscreen")
            }
        }
        composable("thirdscreen"){
            ThirdScreen {
                navController.popBackStack()
            }
        }
    }
}
