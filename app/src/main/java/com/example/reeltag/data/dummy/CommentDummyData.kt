package com.example.reeltag.data.dummy

import com.example.reeltag.data.model.Comment

object CommentDummyData {

    // Trending tags in English
    val trendingTags = listOf("Coldplay Jakarta", "Music Of The Spheres", "Coldplay Live")

    val comments = listOf(
        Comment(
            id = 1,
            reelId = 1,
            username = "chris_fan",
            content = "The atmosphere at Coldplay Jakarta was absolutely magical!",
            likes = 1250,
            timestamp = "2h"
        ),
        Comment(
            id = 2,
            reelId = 1,
            username = "concert_enthusiast",
            content = "The visuals for Music Of The Spheres were mind-blowing. Best night ever!",
            likes = 890,
            timestamp = "4h"
        ),
        Comment(
            id = 3,
            reelId = 1,
            username = "globetrotter",
            content = "I'm still recovering from how amazing Coldplay Live was in Jakarta.",
            likes = 450,
            timestamp = "6h"
        )
    )
}