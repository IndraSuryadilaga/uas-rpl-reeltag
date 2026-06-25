package com.example.reeltag.data.model

data class Comment(

    val id: Int,

    val reelId: Int,

    val username: String,

    val content: String,

    val likes: Int

)