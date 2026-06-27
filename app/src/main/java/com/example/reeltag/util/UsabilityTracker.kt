package com.example.reeltag.util

import com.example.reeltag.data.model.UsabilityResult
import com.example.reeltag.data.repository.UsabilityRepository

object UsabilityTracker {
    private var currentSessionId: String = ""
    private var currentMode: SessionMode = SessionMode.ORIGINAL
    private var startTime: Long = 0L
    private var clickCount: Int = 0
    private var taskSuccess: Boolean = false
    private var sessionCounter: Int = 1

    fun startTask(mode: SessionMode) {
        currentSessionId = "SESSION-${sessionCounter.toString().padStart(3, '0')}"
        currentMode = mode
        startTime = System.currentTimeMillis()
        clickCount = 0
        taskSuccess = false
        sessionCounter++
    }

    fun increaseClick() {
        if (startTime > 0) { // Hanya menghitung jika sesi sedang berjalan
            clickCount++
        }
    }

    fun setTaskSuccess(success: Boolean) {
        if (startTime > 0) {
            taskSuccess = success
        }
    }

    fun finishTask() {
        if (startTime == 0L) return // Mencegah finishTask dipanggil berulang kali

        val endTime = System.currentTimeMillis()
        val timeOnTaskInSeconds = (endTime - startTime) / 1000

        val result = UsabilityResult(
            sessionId = currentSessionId,
            mode = currentMode,
            timeOnTask = timeOnTaskInSeconds,
            clickCount = clickCount,
            taskSuccess = taskSuccess
        )

        UsabilityRepository.saveResult(result)

        // Reset waktu agar sesi tertutup
        startTime = 0L
    }
}