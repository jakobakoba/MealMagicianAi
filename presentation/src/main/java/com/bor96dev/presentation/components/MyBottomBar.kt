package com.bor96dev.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bor96dev.presentation.navigation.Routes
import com.bor96dev.presentation.navigation.Screen

@Composable
fun MyBottomBar(navController: NavController) {
    val items = listOf(
        Screen.FavoriteRecipes,
        Screen.Identification,
        Screen.MyFridge
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(
                icon = {Icon(screen.icon, contentDescription = screen.title)},
                label = {Text(screen.title)},
                selected = currentDestination?.hierarchy?.any{it.route == screen.route} == true,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}