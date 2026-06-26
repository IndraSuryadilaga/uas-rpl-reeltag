package com.example.reeltag.data.dummy

import com.example.reeltag.data.model.Comment

object CommentDummyData {

    val comments = listOf(
        Comment(
            id = 1,
            reelId = 1,
            username = "user_01",
            content = "Coldplay Jakarta benar-benar luar biasa!",
            likes = 542
        ),
        Comment(
            id = 2,
            reelId = 1,
            username = "user_02",
            content = "Konser terbaik tahun ini \uD83D\uDD25",
            likes = 321
        ),
        Comment(
            id = 3,
            reelId = 1,
            username = "user_03",
            content = "Siapa yang sudah nonton hari kedua?",
            likes = 189
        )
    )

}