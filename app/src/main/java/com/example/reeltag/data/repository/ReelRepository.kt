package com.example.reeltag.data.repository

import com.example.reeltag.data.dummy.ReelsDummyData
import com.example.reeltag.data.model.Comment
import com.example.reeltag.data.model.Reel

class ReelRepository {

    fun getReel(): Reel {

        return ReelsDummyData.reel

    }

    fun getComments(): List<Comment> {

        return ReelsDummyData.comments

    }

}