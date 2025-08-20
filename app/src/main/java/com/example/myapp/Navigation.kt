package com.example.myapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(viewModel: ViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstscreen") {
        composable("firstscreen") {
            FirstScreen(NavigateToRegister = {
                navController.navigate("registerscreen")
            }) { name ->
                navController.navigate("secondscreen/$name")
            }


        }
        composable("registerscreen"){
            registerscreen(
                onBack={navController.popBackStack()}
            )
        }
        composable("secondscreen/{name}") { backstackentry ->
            val n = backstackentry.arguments?.getString("name") ?: ""
            SecondScreen(n, NavigateToSecondScreen = {
                navController.navigate("thirdscreen")
            },
                viewModel=viewModel
            )

        }
        composable("thirdscreen") {
            ThirdScreen(
                NavigateToThirdScreen =
                    {
                        navController.popBackStack()
                    },
                viewModel = viewModel

            )
        }
    }
}