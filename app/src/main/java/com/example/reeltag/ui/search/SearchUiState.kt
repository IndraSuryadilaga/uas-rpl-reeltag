package com.example.reeltag.ui.search

import com.example.reeltag.data.model.RelatedContent

data class SearchUiState(

    val query: String = "",

    val recentSearches: List<String> = emptyList(),

    val searchResults: List<RelatedContent> = emptyList(),

    val hashtags: List<String> = emptyList(),

    val isSearching: Boolean = false

)