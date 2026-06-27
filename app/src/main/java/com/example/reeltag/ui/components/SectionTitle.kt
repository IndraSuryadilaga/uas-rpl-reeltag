package com.example.reeltag.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SectionTitle(
    title: String
) {

    Text(
        text = title,
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 12.dp
        ),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )

}