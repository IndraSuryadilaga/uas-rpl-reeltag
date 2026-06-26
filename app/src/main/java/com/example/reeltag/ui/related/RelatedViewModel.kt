package com.example.reeltag.ui.related

import androidx.lifecycle.ViewModel
import com.example.reeltag.data.repository.RelatedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RelatedViewModel : ViewModel() {

    private val repository = RelatedRepository()

    private val _uiState = MutableStateFlow(RelatedUiState())
    val uiState: StateFlow<RelatedUiState> =
        _uiState.asStateFlow()

    fun loadRelatedContent(tag: String) {

        _uiState.value = RelatedUiState(

            selectedTag = tag,

            relatedReels = repository.getRelatedReels(tag),

            relatedPosts = repository.getRelatedPosts(tag),

            relatedHashtags = repository.getRelatedHashtags(tag),

            isLoading = false

        )

    }

}