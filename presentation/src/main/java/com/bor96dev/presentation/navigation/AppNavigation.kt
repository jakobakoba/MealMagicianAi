package com.bor96dev.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bor96dev.presentation.myfridge.MyFridgeScreen
import com.bor96dev.presentation.recipes.RecipeResultsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MY_FRIDGE){
        composable(Routes.MY_FRIDGE){
            MyFridgeScreen(navController)
        }
        composable(Routes.RECIPE_RESULTS){
            RecipeResultsScreen(navController)
        }
    }
}