package com.example.reeltag.ui.reels

import androidx.lifecycle.ViewModel
import com.example.reeltag.data.repository.ReelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReelsViewModel : ViewModel() {

    private val repository = ReelRepository()

    private val _uiState = MutableStateFlow(

        ReelsUiState(

            reel = repository.getReel(),

            comments = repository.getComments()

        )

    )

    val uiState: StateFlow<ReelsUiState> =
        _uiState.asStateFlow()

}