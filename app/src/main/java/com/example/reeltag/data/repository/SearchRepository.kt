package com.example.reeltag.data.repository

import com.example.reeltag.data.model.RelatedContent

class SearchRepository(
    private val relatedRepository: RelatedRepository = RelatedRepository()
) {

    fun getRecentSearches(): List<String> {
        return listOf(
            "Coldplay Jakarta",
            "Music Of The Spheres",
            "Coldplay Live"
        )
    }

    fun search(query: String): List<RelatedContent> {

        if (query.isBlank()) {
            return emptyList()
        }

        val reels = relatedRepository.getRelatedReels(query)
        val posts = relatedRepository.getRelatedPosts(query)

        return reels + posts
    }

    fun getRelatedHashtags(query: String): List<String> {
        return relatedRepository.getRelatedHashtags(query)
    }
}