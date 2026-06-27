package com.example.reeltag.ui.analysis

import com.example.reeltag.data.model.UsabilityResult

data class AnalysisUiState(
    val results: List<UsabilityResult> = emptyList(),
    val averageOriginalTime: Double? = null,
    val averageReelTagTime: Double? = null,
    val timeImprovementPercent: Double? = null,
    val averageOriginalClicks: Double? = null,
    val averageReelTagClicks: Double? = null,
    val clickReductionPercent: Double? = null
)
