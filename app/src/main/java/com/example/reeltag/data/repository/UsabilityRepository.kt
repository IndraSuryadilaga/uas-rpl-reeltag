package com.example.reeltag.data.repository

import com.example.reeltag.data.model.UsabilityResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object UsabilityRepository {
    private val _results = MutableStateFlow<List<UsabilityResult>>(emptyList())
    val results: StateFlow<List<UsabilityResult>> = _results.asStateFlow()

    fun saveResult(result: UsabilityResult) {
        _results.value = _results.value + result
    }
}