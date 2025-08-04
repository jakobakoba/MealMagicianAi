package com.bor96dev.presentation.myfridge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun MyFridgeScreen(
    navController: NavController,
    viewModel: MyFridgeViewModel = hiltViewModel()
) {

    val products by viewModel.products.collectAsStateWithLifecycle()
    val recipes by viewModel.recipes.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Продукт") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                viewModel.addProduct(text)
                text = ""
            }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Добавить")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Твои продукты: ${products.joinToString(", ")}")

        Spacer(modifier = Modifier.height(16.dp))

        Button (
            onClick = {viewModel.findRecipes()},
            enabled = products.isNotEmpty() && !isLoading,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Найти рецепты")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box (modifier = Modifier.fillMaxSize()){
            if (isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)){
                    items(recipes){recipe ->
                        Card(modifier = Modifier.fillMaxWidth()){
                            Column(Modifier.padding(16.dp)){
                                Text(text = recipe.title, style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}