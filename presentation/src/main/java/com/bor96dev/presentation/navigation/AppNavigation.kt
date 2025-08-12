package com.bor96dev.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bor96dev.presentation.camera.CameraScreen
import com.bor96dev.presentation.favorites.FavoriteRecipesScreen
import com.bor96dev.presentation.myfridge.MyFridgeScreen

@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.MY_FRIDGE,
        modifier = Modifier.padding(innerPadding),
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable(Routes.MY_FRIDGE){
            MyFridgeScreen(navController)
        }
        composable(Routes.IDENTIFICATION){
            CameraScreen(navController)
        }
        composable(Routes.FAVORITE_RECIPES){
            FavoriteRecipesScreen(navController)
        }
    }
}