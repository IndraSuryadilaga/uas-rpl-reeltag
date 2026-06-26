package com.example.reeltag.data.model

data class RelatedContent(

    val id: Int,

    val title: String,

    val imageResId: Int,

    val views: String,

    val category: RelatedCategory

)

enum class RelatedCategory {

    REEL,

    POST,

    HASHTAG

}