package com.bor96dev.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.bor96dev.presentation.navigation.Routes

@Composable
fun MyBottomBar(navController: NavController) {
    BottomAppBar {
        IconButton(onClick = {navController.navigate(Routes.FAVORITE_RECIPES)}) {
            Icon(Icons.Default.Favorite, contentDescription = "favorite_recipes")
        }
        IconButton(onClick = {navController.navigate(Routes.RECIPE_RESULTS)}) {
            Icon(Icons.Default.Favorite, contentDescription = "recipe_results")
        }
        IconButton(onClick = {navController.navigate(Routes.MY_FRIDGE)}) {
            Icon(Icons.Default.Favorite, contentDescription = "my_fridge")
        }
    }
}