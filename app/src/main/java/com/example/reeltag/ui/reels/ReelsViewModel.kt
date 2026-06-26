package com.example.reeltag.ui.reels

import androidx.lifecycle.ViewModel
import com.example.reeltag.data.repository.CommentRepository
import com.example.reeltag.data.repository.ReelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReelsViewModel : ViewModel() {

    private val reelRepository = ReelRepository()
    private val commentRepository = CommentRepository()

    private val reelData = reelRepository.getReel()
    private val commentsData = commentRepository.getCommentsByReelId(reelData.id)

    private val _uiState = MutableStateFlow(
        ReelsUiState(
            reel = reelData,
            comments = commentsData
        )
    )

    val uiState: StateFlow<ReelsUiState> = _uiState.asStateFlow()

}