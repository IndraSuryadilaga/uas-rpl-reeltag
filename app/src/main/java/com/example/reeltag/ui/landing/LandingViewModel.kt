package com.example.reeltag.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reeltag.data.model.UsabilityResult
import com.example.reeltag.data.repository.UsabilityRepository
import com.example.reeltag.util.SessionMode
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LandingViewModel : ViewModel() {

    val uiState: StateFlow<LandingUiState> =
        UsabilityRepository.results
            .map(::mapResultsToUiState)
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = LandingUiState()
            )

    private fun mapResultsToUiState(results: List<UsabilityResult>): LandingUiState {
        val originalCompleted =
            results.any { it.mode == SessionMode.ORIGINAL }

        val reelTagCompleted =
            results.any { it.mode == SessionMode.REELTAG }

        return LandingUiState(
            originalCompleted = originalCompleted,
            reelTagCompleted = reelTagCompleted,
            analysisEnabled = true
        )
    }

}
