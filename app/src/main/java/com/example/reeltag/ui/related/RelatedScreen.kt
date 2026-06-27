package com.example.reeltag.ui.related

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import com.example.reeltag.ui.components.RelatedCard
import com.example.reeltag.ui.components.HashtagChip
import com.example.reeltag.ui.components.SectionTitle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reeltag.util.UsabilityTracker

@Composable
fun RelatedScreen(
    title: String,
    selectedTag: String,
    showCloseButton: Boolean,
    onClose: () -> Unit,
    onBack: () -> Unit = {},
    viewModel: RelatedViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(selectedTag) {
        viewModel.loadRelatedContent(selectedTag)
        UsabilityTracker.setTaskSuccess(true)
        // US-11: Selesaikan sesi pelacakan
        UsabilityTracker.finishTask()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Header(
            title = title,
            tag = selectedTag,
            showCloseButton = showCloseButton,
            onClose = onClose,
            onBack = onBack
        )

        HorizontalDivider(color = Color.DarkGray)

        SectionTitle(
            title = "Related Reels"
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(uiState.relatedReels) { reel ->
                RelatedCard(
                    title = reel.title,
                    imageRes = reel.imageResId,
                    subtitle = "${reel.views} views",
                    onClick = {
                        UsabilityTracker.increaseClick()
                        // Tambahkan navigasi ke detail video di sini
                    }
                )

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle(
            title = "Related Posts"
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(uiState.relatedPosts) { post ->

                Image(
                    painter = painterResource(id = post.imageResId),
                    contentDescription = post.title,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle(
            title = "Related Hashtags"
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.relatedHashtags) { tag ->
                HashtagChip(
                    hashtag = tag,
                    onClick = {
                        // US-11: Tambahkan tracking saat user klik hashtag terkait lainnya
                        UsabilityTracker.increaseClick()
                        // Tambahkan navigasi ke tag lain
                    }
                )
            }
        }
    }
}

@Composable
private fun Header(
    title: String,
    tag: String,
    showCloseButton: Boolean,
    onClose: () -> Unit,
    onBack: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 18.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )

            Text(
                text = tag,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

        }

        if (showCloseButton) {

            IconButton(
                onClick = onClose
            ) {

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White
                )

            }

        } else {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )

            }

        }

    }
}
