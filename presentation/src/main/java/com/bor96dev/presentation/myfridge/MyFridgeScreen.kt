package com.bor96dev.presentation.myfridge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MyFridgeScreen(
    navController: NavController,
    viewModel: MyFridgeViewModel = hiltViewModel()
) {

    val products by viewModel.products.collectAsState()
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(8.dp)) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Продукт") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                viewModel.addProduct(text)
                text = ""
            }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Добавить")
            }
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(products) { product ->
                Text(text = product, modifier = Modifier.padding(16.dp))
            }
        }

        Button(
            onClick = {
                val ingredients = products.joinToString(",")
                navController.navigate("recipe_results_screen/$ingredients")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            enabled = products.isNotEmpty()
        ) {
            Text("Найти рецепты")
        }
    }
}