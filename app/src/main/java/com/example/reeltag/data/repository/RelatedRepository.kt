package com.example.reeltag.data.repository

import com.example.reeltag.data.dummy.RelatedDummyData
import com.example.reeltag.data.model.RelatedCategory
import com.example.reeltag.data.model.RelatedContent

class RelatedRepository {

    fun getRelatedReels(
        tag: String
    ): List<RelatedContent> {

        return RelatedDummyData
            .getRelatedContent(tag)
            .filter {

                it.category == RelatedCategory.REEL

            }

    }

    fun getRelatedPosts(
        tag: String
    ): List<RelatedContent> {

        return RelatedDummyData
            .getRelatedContent(tag)
            .filter {

                it.category == RelatedCategory.POST

            }

    }

    fun getRelatedHashtags(
        tag: String
    ): List<String> {

        return RelatedDummyData
            .getRelatedHashtags(tag)

    }

}