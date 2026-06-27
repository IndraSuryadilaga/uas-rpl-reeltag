package com.example.reeltag.data.model

import com.example.reeltag.util.SessionMode

data class UsabilityResult(
    val sessionId: String,
    val mode: SessionMode,
    val timeOnTask: Long, // Dicatat dalam detik (seconds)
    val clickCount: Int,
    val taskSuccess: Boolean
)