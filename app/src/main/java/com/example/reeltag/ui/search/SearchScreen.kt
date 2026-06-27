package com.example.reeltag.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reeltag.ui.components.BottomNavigationBar
import com.example.reeltag.ui.components.HashtagChip
import com.example.reeltag.ui.components.RelatedCard
import com.example.reeltag.ui.components.SectionTitle

@Composable
fun SearchScreen(
    onBack: () -> Unit,
    onReelsClick: () -> Unit,
    viewModel: SearchViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "search",
                onSearchClick = { },
                onReelsClick = onReelsClick
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
        ) {

            SearchHeader(
                query = uiState.query,
                onQueryChange = viewModel::onQueryChange,
                onBack = onBack
            )

            HorizontalDivider(
                color = Color.DarkGray
            )

            SectionTitle(
                title = "Recent Search"
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.recentSearches) { keyword ->
                    HashtagChip(
                        hashtag = keyword,
                        onClick = {
                            viewModel.selectRecentSearch(keyword)
                        }
                    )
                }
            }

            if (uiState.searchResults.isNotEmpty()) {
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                SectionTitle(
                    title = "Related Reels"
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.searchResults) { reel ->
                        RelatedCard(
                            title = reel.title,
                            imageRes = reel.imageResId,
                            subtitle = "${reel.views} views"
                        )
                    }
                } // Perbaikan: sebelumnya )
            }

            if (uiState.hashtags.isNotEmpty()) {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                SectionTitle(
                    title = "Related Hashtags"
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.hashtags) { hashtag ->
                        HashtagChip(
                            hashtag = hashtag
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchHeader(
    query: String,
    onQueryChange: (String) -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Search")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions()
        )
    }
}