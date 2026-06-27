package com.example.reeltag.ui.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun HashtagChip(
    hashtag: String,
    onClick: () -> Unit = {}
) {

    AssistChip(
        onClick = onClick,

        label = {
            Text(text = hashtag)
        },

        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color.Transparent,
            labelColor = Color.White
        ),

        border = AssistChipDefaults.assistChipBorder(
            enabled = true,
            borderColor = Color.White
        )
    )
}
