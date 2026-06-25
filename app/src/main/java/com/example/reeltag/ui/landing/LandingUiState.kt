package com.example.reeltag.ui.landing

data class LandingUiState(

    val appTitle: String = "ReelTag",

    val appDescription: String =
        "Prototype penelitian untuk meningkatkan content discovery pada Instagram Reels.",

    val originalCompleted: Boolean = false,

    val reelTagCompleted: Boolean = false,

    val analysisEnabled: Boolean = false

)