package com.bor96dev.presentation.myfridge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bor96dev.presentation.R
import com.bor96dev.presentation.components.RecipeCard

@Composable
fun MyFridgeScreen(
    navController: NavController,
    viewModel: MyFridgeViewModel = hiltViewModel()
) {

    val (state, ingredients) = viewModel.combinedState.collectAsStateWithLifecycle().value


    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)){
            Spacer(modifier = Modifier.height(16.dp))
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

            Text(
                text = "Твои продукты:",
                style = MaterialTheme.typography.titleLarge
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                if (ingredients.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Вы еще не добавили продукты")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(ingredients) { ingredient ->
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    text = "• ${ingredient.name}",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Medium
                                    ),
                                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                )
                                IconButton (onClick = {viewModel.removeIngredient(ingredient)}){
                                    Icon (
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Удалить"
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button (
                onClick = viewModel::findRecipes,
                enabled = ingredients.isNotEmpty() && !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Найти рецепты")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            if (state.searchResults.isEmpty()){
                Box (modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.no_recipes),
                        contentDescription = "no recipes",
                        modifier = Modifier.size(150.dp)
                    )
                }

            }
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.searchResults) {recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onFavoriteClick = {clickedRecipe ->
                            if(clickedRecipe.isFavorite){
                                viewModel.removeRecipeFromFavorites(clickedRecipe)
                            } else {
                                viewModel.addRecipeToFavorites(clickedRecipe)
                            }
                        },
                        onRecipeClick = {
                            TODO()
                        }
                    )
                }
            }
        }
    }
}