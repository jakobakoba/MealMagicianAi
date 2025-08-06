package com.bor96dev.presentation.myfridge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bor96dev.presentation.components.RecipeCard

@Composable
fun MyFridgeScreen(
    navController: NavController,
    viewModel: MyFridgeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = state.currentIngredientInput,
                onValueChange = viewModel::onIngredientInputChange,
                label = { Text("Продукт") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = viewModel::addIngredient,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Добавить")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Твои продукты:")
        LazyColumn(modifier = Modifier.height(150.dp)) {
            items(state.ingredients){ingredient ->
                Text(ingredient, modifier = Modifier.padding(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button (
            onClick = viewModel::findRecipes,
            enabled = state.ingredients.isNotEmpty() && !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Найти рецепты")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            LazyColumn {
                items(state.searchResults) {recipe ->
                    RecipeCard(
                        recipe = recipe,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}