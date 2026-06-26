package com.example.reeltag.ui.reels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reeltag.ui.components.VideoPlayer

@Composable
fun ReelsScreen(
    onCommentClick: () -> Unit,
    viewModel: ReelsViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val reel = uiState.reel ?: return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        VideoPlayer(
            videoUri = reel.videoUri,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

        ) {

            ReelAction(
                icon = Icons.Default.Favorite,
                count = formatCount(reel.likes)
            )

            Spacer(modifier = Modifier.size(20.dp))

            ReelAction(
                icon = Icons.Default.Chat,
                count = uiState.comments.size.toString(),
                onClick = onCommentClick
            )

            Spacer(modifier = Modifier.size(20.dp))

            ReelAction(
                icon = Icons.Default.Send,
                count = formatCount(reel.shares)
            )

            Spacer(modifier = Modifier.size(20.dp))

            ReelAction(
                icon = Icons.Default.BookmarkBorder,
                count = formatCount(reel.bookmarks)
            )

        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "@${reel.username}",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = reel.caption,
                color = Color.White
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = reel.music,
                color = Color.White
            )

        }

    }

}

@Composable
private fun ReelAction(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    count: String,
    onClick: () -> Unit = {}
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(
            onClick = onClick
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )

        }

        Text(
            text = count,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )

    }

}

private fun formatCount(value: Int): String {

    return when {

        value >= 1_000_000 ->
            String.format("%.1fM", value / 1_000_000f)

        value >= 1_000 ->
            String.format("%.1fK", value / 1_000f)

        else ->
            value.toString()

    }

}