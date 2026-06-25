package com.example.reeltag.ui.reels

import com.example.reeltag.data.model.Comment
import com.example.reeltag.data.model.Reel

data class ReelsUiState(

    val reel: Reel? = null,

    val comments: List<Comment> = emptyList(),

    val isLoading: Boolean = false

)