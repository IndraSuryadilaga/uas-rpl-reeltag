package com.example.reeltag.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object UsabilitySessionManager {

    private val _sessionMode =
        MutableStateFlow(SessionMode.ORIGINAL)

    val sessionMode: StateFlow<SessionMode> =
        _sessionMode.asStateFlow()

    fun startOriginalSession() {
        _sessionMode.value = SessionMode.ORIGINAL
        // PERBAIKAN: Memulai pencatatan task untuk mode Original
        UsabilityTracker.startTask(SessionMode.ORIGINAL)
    }

    fun startReelTagSession() {
        _sessionMode.value = SessionMode.REELTAG
        // PERBAIKAN: Memulai pencatatan task untuk mode ReelTag
        UsabilityTracker.startTask(SessionMode.REELTAG)
    }

    fun isOriginalMode(): Boolean {
        return _sessionMode.value == SessionMode.ORIGINAL
    }

    fun isReelTagMode(): Boolean {
        return _sessionMode.value == SessionMode.REELTAG
    }

    fun reset() {
        _sessionMode.value = SessionMode.ORIGINAL
    }
}