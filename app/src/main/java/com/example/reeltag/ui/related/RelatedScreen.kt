package com.example.reeltag.ui.related

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
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

@Composable
fun RelatedScreen(
    selectedTag: String,
    onClose: () -> Unit,
    viewModel: RelatedViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(selectedTag) {
        viewModel.loadRelatedContent(selectedTag)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Header(
            tag = selectedTag,
            onClose = onClose
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
                    subtitle = "${reel.views} views"
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

                AssistChip(
                    onClick = { },

                    label = {
                        Text(text = tag)
                    },

                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color.Transparent,
                        labelColor = Color(0xFF38BDF8)
                    ),

                    border = AssistChipDefaults.assistChipBorder(
                        enabled = true,
                        borderColor = Color(0xFF38BDF8)
                    )
                )

            }

        }

    }

}

@Composable
private fun Header(
    tag: String,
    onClose: () -> Unit
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
                text = "Related Content",
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

        IconButton(
            onClick = onClose
        ) {

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White
            )

        }

    }

}

@Composable
private fun SectionTitle(
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

@Composable
private fun RelatedCard(
    title: String,
    imageRes: Int,
    subtitle: String
) {

    Card(
        modifier = Modifier.width(170.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {

        Column {

            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = subtitle,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }

    }

}