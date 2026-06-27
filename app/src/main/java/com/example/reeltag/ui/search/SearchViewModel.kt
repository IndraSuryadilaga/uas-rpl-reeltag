package com.example.reeltag.ui.search

import androidx.lifecycle.ViewModel
import com.example.reeltag.data.model.RelatedContent
import com.example.reeltag.data.repository.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel(
    private val repository: SearchRepository = SearchRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SearchUiState(
            recentSearches = repository.getRecentSearches()
        )
    )
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onQueryChange(query: String) {

        _uiState.update {
            it.copy(
                query = query,
                isSearching = false,
                searchResults = if (query.isBlank()) emptyList() else it.searchResults,
                hashtags = if (query.isBlank()) emptyList() else it.hashtags
            )
        }
    }

    fun submitSearch() {
        val query =
            _uiState.value.query.trim()

        _uiState.update {
            it.copy(
                query = query,
                isSearching = query.isNotBlank(),
                searchResults = repository.search(query),
                hashtags = repository.getRelatedHashtags(query)
            )
        }
    }

    fun clearSearch() {

        _uiState.update {
            it.copy(
                query = "",
                isSearching = false,
                searchResults = emptyList()
            )
        }
    }

    fun selectRecentSearch(keyword: String) {

        _uiState.update {
            it.copy(
                query = keyword,
                isSearching = true,
                searchResults = repository.search(keyword),
                hashtags = repository.getRelatedHashtags(keyword)
            )
        }
    }
}
