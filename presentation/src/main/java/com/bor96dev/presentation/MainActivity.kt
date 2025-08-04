package com.bor96dev.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bor96dev.presentation.components.MyBottomBar
import com.bor96dev.presentation.navigation.AppNavigation
import com.bor96dev.presentation.ui.theme.MealMagicianAiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            MealMagicianAiTheme {
                Scaffold(bottomBar = {
                    MyBottomBar(navController)
                }, modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavigation(navController, innerPadding)
                }
            }
        }
    }
}

