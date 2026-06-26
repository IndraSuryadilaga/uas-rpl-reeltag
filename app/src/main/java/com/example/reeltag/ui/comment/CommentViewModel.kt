package com.example.reeltag.ui.comment

import androidx.lifecycle.ViewModel
import com.example.reeltag.data.repository.CommentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommentViewModel : ViewModel() {

    private val repository = CommentRepository()

    private val _uiState = MutableStateFlow(CommentUiState())
    val uiState: StateFlow<CommentUiState> = _uiState.asStateFlow()

    fun loadComments(reelId: Int, isReelTagMode: Boolean) {
        _uiState.value = CommentUiState(
            comments = repository.getCommentsByReelId(reelId),
            trendingTags = if (isReelTagMode) repository.getTrendingTags() else emptyList(),
            isReelTagMode = isReelTagMode
        )
    }

}