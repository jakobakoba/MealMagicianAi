package com.bor96dev.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val route: String, val title: String, val icon: ImageVector) {
    object FavoriteRecipes : Screen(Routes.FAVORITE_RECIPES, "Favorite Recipes", Icons.Default.Favorite)
    object Identification : Screen(Routes.IDENTIFICATION, "AI Identification", Icons.Default.Search)
    object MyFridge : Screen(Routes.MY_FRIDGE, "My Fridge", Icons.Default.ShoppingCart)
}