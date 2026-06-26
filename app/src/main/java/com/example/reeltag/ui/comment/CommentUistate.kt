package com.example.reeltag.ui.comment

import com.example.reeltag.data.model.Comment

data class CommentUiState(
    val comments: List<Comment> = emptyList(),
    val trendingTags: List<String> = emptyList(),
    val isReelTagMode: Boolean = false
)