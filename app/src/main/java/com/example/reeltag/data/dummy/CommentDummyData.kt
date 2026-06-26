package com.example.reeltag.data.dummy

import com.example.reeltag.data.model.Comment

object CommentDummyData {

    val trendingTags = listOf("Travel Jepang", "iPhone 17", "Coldplay")

    val comments = listOf(
        Comment(
            id = 1,
            reelId = 1,
            username = "User A",
            content = "This reminds me of my trip to Travel Jepang",
            likes = 24,
            timestamp = "2h"
        ),
        Comment(
            id = 2,
            reelId = 1,
            username = "User B",
            content = "I can't wait for the iPhone 17 launch! The camera specs look insane.",
            likes = 152,
            timestamp = "4h"
        ),
        Comment(
            id = 3,
            reelId = 1,
            username = "User C",
            content = "Have you guys seen the new Coldplay tour? The visuals are incredible!",
            likes = 89,
            timestamp = "5h"
        )
    )

}