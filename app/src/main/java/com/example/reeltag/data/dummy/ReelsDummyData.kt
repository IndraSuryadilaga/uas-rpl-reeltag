package com.example.reeltag.data.dummy

import com.example.reeltag.data.model.Comment
import com.example.reeltag.data.model.Reel

object ReelsDummyData {

    val reel = Reel(

        id = 1,

        username = "coldplay",

        caption = "Music Of The Spheres World Tour 🌎✨\nJakarta was unforgettable! ❤️",

        music = "Coldplay • A Sky Full of Stars",

        likes = 1200000,

        shares = 56000,

        bookmarks = 42000

    )

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
            content = "Konser terbaik tahun ini 🔥",
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