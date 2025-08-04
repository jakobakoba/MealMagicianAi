package com.bor96dev.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bor96dev.domain.Ingredient

@Composable
fun IngredientSection(
    title: String, ingredients: List<Ingredient>, color: Color
) {
    Column(modifier = Modifier.fillMaxWidth()){
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        ingredients.forEach { ingredient ->
            Text(
                text = ". ${ingredient.name} (${ingredient.amount} ${ingredient.unit})",
                color = color,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
