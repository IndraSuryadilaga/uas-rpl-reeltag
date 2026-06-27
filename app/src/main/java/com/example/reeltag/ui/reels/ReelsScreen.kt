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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reeltag.ui.components.VideoPlayer
import com.example.reeltag.ui.comment.CommentBottomSheet
import java.util.Locale
import com.example.reeltag.util.SessionMode
import com.example.reeltag.util.UsabilitySessionManager

// Added Imports
import com.example.reeltag.navigation.Screen
import com.example.reeltag.ui.components.BottomNavigationBar
import com.example.reeltag.util.UsabilityTracker

@Composable
fun ReelsScreen(
    onTagClick: (String) -> Unit = {},
    onSearchClick: () -> Unit = {},
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

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "reels",
                onSearchClick = onSearchClick,
                onReelsClick = {}
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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

                Spacer(modifier = Modifier.height(30.dp))

                ReelAction(
                    icon = Icons.AutoMirrored.Filled.Chat,
                    count = uiState.comments.size.toString(),
                    onClick = {
                        UsabilityTracker.increaseClick()
                        showComments.value = true
                    }
                )

                Spacer(modifier = Modifier.height(30.dp))

                ReelAction(
                    icon = Icons.AutoMirrored.Filled.Send,
                    count = formatCount(reel.shares)
                )

                Spacer(modifier = Modifier.height(30.dp))

                ReelAction(
                    icon = Icons.Default.BookmarkBorder,
                    count = formatCount(reel.bookmarks)
                )
            }

            if (showComments.value) {
                CommentBottomSheet(
                    reelId = reel.id,
                    isReelTagMode = isReelTagMode,
                    onDismiss = {
                        showComments.value = false
                    },
                    onTagClick = { tag ->
                        showComments.value = false
                        onTagClick(tag)
                    }
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {
                Text(
                    text = "@${reel.username}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = reel.caption, color = Color.White)

                // --- BAGIAN TAMBAHAN: Menampilkan Tags secara horizontal ---
                if (reel.tags.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
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

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = reel.music,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(34.dp)
            )
        }
        Text(
            text = count,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

private fun formatCount(value: Int): String {
    return when {
        value >= 1_000_000 -> String.format(Locale.US, "%.1fM", value / 1_000_000f)
        value >= 1_000 -> String.format(Locale.US, "%.1fK", value / 1_000f)
        else -> value.toString()
    }
}
