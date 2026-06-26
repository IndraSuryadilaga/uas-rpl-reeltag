package com.example.reeltag.ui.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reeltag.data.model.Comment

// Warna kustom berdasarkan mockup
val DarkSurface = Color(0xFF1E1E1E)
val LinkBlue = Color(0xFF38BDF8)
val DividerGray = Color(0xFF333333)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentBottomSheet(
    reelId: Int,
    isReelTagMode: Boolean,
    onDismiss: () -> Unit,
    onTagClick: (String) -> Unit,
    viewModel: CommentViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(reelId, isReelTagMode) {
        viewModel.loadComments(reelId, isReelTagMode)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = DarkSurface,
        dragHandle = { BottomSheetDefaults.DragHandle(color = Color.Gray) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(24.dp)) // Keseimbangan layout
                Text(
                    text = "Comments",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(onClick = onDismiss, modifier = Modifier.size(24.dp)) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                }
            }

            HorizontalDivider(color = DividerGray, thickness = 1.dp)

            // ReelTag Trending Section (Hanya muncul di mode ReelTag)
            if (isReelTagMode && uiState.trendingTags.isNotEmpty()) {
                Column(modifier = Modifier.padding(vertical = 12.dp)) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "TRENDING TOPICS FROM COMMENTS",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.trendingTags) { tag ->
                            TrendingTagChip(tag = tag, onClick = { onTagClick(tag) })
                        }
                    }
                }
                HorizontalDivider(color = DividerGray, thickness = 1.dp)
            }

            // Comments List
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(uiState.comments) { comment ->
                    CommentItem(
                        comment = comment,
                        trendingTags = uiState.trendingTags,
                        isReelTagMode = uiState.isReelTagMode,
                        onTagClick = onTagClick
                    )
                }
                item { Spacer(modifier = Modifier.height(32.dp)) }
            }
        }
    }
}

@Composable
fun TrendingTagChip(tag: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, DividerGray, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = LinkBlue,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = tag,
            color = LinkBlue,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun CommentItem(
    comment: Comment,
    trendingTags: List<String>,
    isReelTagMode: Boolean,
    onTagClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        // Avatar Dummy
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = comment.username.first().toString(), color = Color.White)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = comment.username,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = comment.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            if (isReelTagMode && trendingTags.isNotEmpty()) {
                val inlineContentId = "searchIcon"
                val annotatedString = buildAnnotatedString {
                    var currentIndex = 0
                    val text = comment.content

                    val matches = trendingTags.mapNotNull { tag ->
                        val index = text.indexOf(tag, ignoreCase = true)
                        if (index != -1) Triple(index, index + tag.length, tag) else null
                    }.sortedBy { it.first }

                    for (match in matches) {
                        if (match.first >= currentIndex) {
                            append(text.substring(currentIndex, match.first))

                            // Menambahkan tag anotasi agar teks biru bisa diklik
                            pushStringAnnotation(tag = "TAG", annotation = match.third)
                            withStyle(style = SpanStyle(color = LinkBlue)) {
                                appendInlineContent(inlineContentId, "[icon]")
                                append(" ${match.third}")
                            }
                            pop()
                            currentIndex = match.second
                        }
                    }
                    if (currentIndex < text.length) {
                        append(text.substring(currentIndex))
                    }
                }

                val inlineContent = mapOf(
                    Pair(
                        inlineContentId,
                        InlineTextContent(
                            Placeholder(
                                width = 14.sp,
                                height = 14.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                            )
                        ) {
                            Icon(Icons.Default.Search, contentDescription = null, tint = LinkBlue)
                        }
                    )
                )

                // Menggunakan Text biasa + pointerInput sebagai pengganti ClickableText
                val (layoutResult, setLayoutResult) = remember { mutableStateOf<TextLayoutResult?>(null) }

                Text(
                    text = annotatedString,
                    inlineContent = inlineContent,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                    onTextLayout = { setLayoutResult(it) },
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures { pos ->
                            layoutResult?.let { result ->
                                val offset = result.getOffsetForPosition(pos)
                                annotatedString.getStringAnnotations(tag = "TAG", start = offset, end = offset)
                                    .firstOrNull()?.let { annotation ->
                                        onTagClick(annotation.item)
                                    }
                            }
                        }
                    }
                )
            } else {
                Text(
                    text = comment.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Footer Action
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Reply", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = comment.likes.toString(), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }
    }
}