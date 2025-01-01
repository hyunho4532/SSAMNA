package com.asetec.presentation.ui.tool

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCard(width: Dp, height: Dp, text: String) {
    Card (
        modifier = Modifier
            .width(width)
            .height(height)
            .shadow(
                elevation = 3.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Text(text = text)
    }
}