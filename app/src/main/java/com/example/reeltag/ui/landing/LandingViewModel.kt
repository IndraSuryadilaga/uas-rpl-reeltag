package com.example.reeltag.ui.landing

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LandingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LandingUiState())

    val uiState: StateFlow<LandingUiState> =
        _uiState.asStateFlow()

    fun completeOriginalScenario() {

        _uiState.update {

            it.copy(
                originalCompleted = true,
                analysisEnabled = it.reelTagCompleted
            )

        }

    }

    fun completeReelTagScenario() {

        _uiState.update {

            it.copy(
                reelTagCompleted = true,
                analysisEnabled = it.originalCompleted
            )

        }

    }

    fun resetProgress() {

        _uiState.value = LandingUiState()

    }

}