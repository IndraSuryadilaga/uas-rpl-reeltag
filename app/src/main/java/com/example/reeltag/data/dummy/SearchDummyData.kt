package com.example.reeltag.data.dummy

import com.example.reeltag.data.model.SearchItem

object SearchDummyData {

    val searchItems = listOf(

        SearchItem(
            id = 1,
            keyword = "Coldplay Jakarta"
        ),

        SearchItem(
            id = 2,
            keyword = "Music Of The Spheres"
        ),

        SearchItem(
            id = 3,
            keyword = "Coldplay Live"
        ),

        SearchItem(
            id = 4,
            keyword = "Chris Martin"
        ),

        SearchItem(
            id = 5,
            keyword = "Jakarta Concert"
        )
    )

    val recentSearches = listOf(
        "Coldplay Jakarta",
        "Music Of The Spheres",
        "Coldplay Live"
    )
}