package com.example.reeltag.data.repository

import com.example.reeltag.data.dummy.CommentDummyData
import com.example.reeltag.data.model.Comment

class CommentRepository {

    fun getCommentsByReelId(reelId: Int): List<Comment> {
        return CommentDummyData.comments.filter { it.reelId == reelId }
    }

    // Fungsi ini yang sebelumnya terlewat
    fun getTrendingTags(): List<String> {
        return CommentDummyData.trendingTags
    }

}