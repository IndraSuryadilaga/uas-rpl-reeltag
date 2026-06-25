package com.example.reeltag.data.model

data class Reel(

    val id: Int,

    val username: String,

    val caption: String,

    val music: String,

    val likes: Int,

    val shares: Int,

    val bookmarks: Int,

    val videoUrl: String? = null

)