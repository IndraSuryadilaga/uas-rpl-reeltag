package com.example.reeltag.ui.instruction

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InstructionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InstructionUiState())
    val uiState: StateFlow<InstructionUiState> = _uiState.asStateFlow()

    /**
     * Sementara masih dummy.
     * Pada US-11 akan membaca mode dari UsabilitySessionManager.
     */
    fun getInstruction(isReelTagMode: Boolean): String {
        return if (isReelTagMode) {
            _uiState.value.reelTagInstruction
        } else {
            _uiState.value.originalInstruction
        }
    }

}