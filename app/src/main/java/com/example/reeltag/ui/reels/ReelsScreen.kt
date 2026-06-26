package com.example.reeltag.ui.reels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reeltag.ui.components.VideoPlayer
import com.example.reeltag.ui.comment.CommentBottomSheet
import java.util.Locale
import com.example.reeltag.util.SessionMode
import com.example.reeltag.util.UsabilitySessionManager

@Composable
fun ReelsScreen(
    onCommentClick: () -> Unit = {},
    viewModel: ReelsViewModel = viewModel()
) {

    val showComments = remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()

    val sessionMode by UsabilitySessionManager
        .sessionMode
        .collectAsState()

    val isReelTagMode =
        sessionMode == SessionMode.REELTAG

    LaunchedEffect(sessionMode) {
        println("SESSION MODE = $sessionMode")
    }

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
                icon = Icons.AutoMirrored.Filled.Chat, // Diperbarui ke AutoMirrored
                count = uiState.comments.size.toString(),
                onClick = { showComments.value = true }
            )

            Spacer(modifier = Modifier.size(20.dp))

            ReelAction(
                icon = Icons.AutoMirrored.Filled.Send, // Diperbarui ke AutoMirrored
                count = formatCount(reel.shares)
            )

            Spacer(modifier = Modifier.size(20.dp))

            ReelAction(
                icon = Icons.Default.BookmarkBorder,
                count = formatCount(reel.bookmarks)
            )

        }

        if (showComments.value) {
            CommentBottomSheet(
                reelId = reel.id,
                isReelTagMode = isReelTagMode,
                onDismiss = { showComments.value = false },
                onTagClick = { tag ->
                    // TODO
                }
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
            Text(text = reel.caption, color = Color.White)

            // --- BAGIAN TAMBAHAN: Menampilkan Tags secara horizontal ---
            if (reel.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.size(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(reel.tags) { tag ->
                        Text(
                            text = tag,
                            color = Color(0xFF38BDF8), // Warna biru penanda tag
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

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
        value >= 1_000_000 -> String.format(Locale.US, "%.1fM", value / 1_000_000f) // Ditambahkan Locale.US agar aman
        value >= 1_000 -> String.format(Locale.US, "%.1fK", value / 1_000f)       // Ditambahkan Locale.US agar aman
        else -> value.toString()
    }
}