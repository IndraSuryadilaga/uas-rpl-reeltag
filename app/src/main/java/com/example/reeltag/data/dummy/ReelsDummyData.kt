package com.example.reeltag.data.dummy

import com.example.reeltag.data.model.Reel

object ReelsDummyData {

    val reel = Reel(
        id = 1,
        username = "coldplay",
        caption = "Music Of The Spheres World Tour \uD83C\uDF0E✨\nJakarta was unforgettable! ❤️",
        music = "Coldplay • Fix You",
        likes = 1200000,
        shares = 56000,
        bookmarks = 42000,
        videoUri = "android.resource://com.example.reeltag/raw/reel1",
        tags = listOf("#Coldplay", "#JakartaConcert", "#MusicOfTheSpheres")
    )

}