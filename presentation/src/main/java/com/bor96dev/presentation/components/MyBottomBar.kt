package com.bor96dev.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bor96dev.presentation.navigation.Routes

@Composable
fun MyBottomBar(navController: NavController) {
    BottomAppBar {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = { navController.navigate(Routes.FAVORITE_RECIPES) }) {
                Icon(Icons.Default.Favorite, contentDescription = "favorite_recipes")
            }
            IconButton(onClick = { navController.navigate(Routes.RECIPE_RESULTS) }) {
                Icon(Icons.Default.Search, contentDescription = "recipe_results")
            }
            IconButton(onClick = { navController.navigate(Routes.MY_FRIDGE) }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "my_fridge")
            }
        }
    }
}