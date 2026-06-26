package com.example.reeltag.ui.related

import com.example.reeltag.data.model.RelatedContent

data class RelatedUiState(

    val selectedTag: String = "",

    val relatedReels: List<RelatedContent> = emptyList(),

    val relatedPosts: List<RelatedContent> = emptyList(),

    val relatedHashtags: List<String> = emptyList(),

    val isLoading: Boolean = false

)