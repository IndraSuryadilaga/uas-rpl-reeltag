package com.example.reeltag.ui.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reeltag.data.model.UsabilityResult
import com.example.reeltag.data.repository.UsabilityRepository
import com.example.reeltag.util.SessionMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AnalysisViewModel : ViewModel() {
    val uiState: StateFlow<AnalysisUiState> =
        UsabilityRepository.results
            .map(::toUiState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AnalysisUiState()
            )

    private fun toUiState(results: List<UsabilityResult>): AnalysisUiState {
        val originalResults =
            results.filter { it.mode == SessionMode.ORIGINAL }

        val reelTagResults =
            results.filter { it.mode == SessionMode.REELTAG }

        val averageOriginalTime =
            originalResults.averageOfOrNull { it.timeOnTask.toDouble() }

        val averageReelTagTime =
            reelTagResults.averageOfOrNull { it.timeOnTask.toDouble() }

        val averageOriginalClicks =
            originalResults.averageOfOrNull { it.clickCount.toDouble() }

        val averageReelTagClicks =
            reelTagResults.averageOfOrNull { it.clickCount.toDouble() }

        return AnalysisUiState(
            results = results.sortedByDescending { it.endTime },
            averageOriginalTime = averageOriginalTime,
            averageReelTagTime = averageReelTagTime,
            timeImprovementPercent = calculateImprovement(averageOriginalTime, averageReelTagTime),
            averageOriginalClicks = averageOriginalClicks,
            averageReelTagClicks = averageReelTagClicks,
            clickReductionPercent = calculateImprovement(averageOriginalClicks, averageReelTagClicks)
        )
    }

    private fun calculateImprovement(original: Double?, reelTag: Double?): Double? {
        if (original == null || reelTag == null || original <= 0.0) {
            return null
        }

        return ((original - reelTag) / original) * 100
    }

    private fun <T> List<T>.averageOfOrNull(selector: (T) -> Double): Double? {
        if (isEmpty()) {
            return null
        }

        return map(selector).average()
    }
}
