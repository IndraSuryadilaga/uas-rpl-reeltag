package com.example.reeltag.data.dummy

import com.example.reeltag.R
import com.example.reeltag.data.model.RelatedCategory
import com.example.reeltag.data.model.RelatedContent

object RelatedDummyData {

    private val coldplayContent = listOf(

        RelatedContent(
            id = 1,
            title = "Coldplay Live Jakarta",
            imageResId = R.drawable.coldplay1,
            views = "2.4M",
            category = RelatedCategory.REEL
        ),

        RelatedContent(
            id = 2,
            title = "Music Of The Spheres",
            imageResId = R.drawable.coldplay2,
            views = "850K",
            category = RelatedCategory.REEL
        ),

        RelatedContent(
            id = 3,
            title = "Fix You Live",
            imageResId = R.drawable.coldplay3,
            views = "1.1M",
            category = RelatedCategory.REEL
        ),

        RelatedContent(
            id = 4,
            title = "Coldplay Stage",
            imageResId = R.drawable.post1,
            views = "",
            category = RelatedCategory.POST
        ),

        RelatedContent(
            id = 5,
            title = "Music Performance",
            imageResId = R.drawable.post2,
            views = "",
            category = RelatedCategory.POST
        ),

        RelatedContent(
            id = 6,
            title = "Coldplay Band",
            imageResId = R.drawable.post3,
            views = "",
            category = RelatedCategory.POST
        )

    )

    private val hashtags = listOf(

        "#Coldplay",

        "#Music",

        "#Concert",

        "#FixYou",

        "#VivaLaVida"

    )

    fun getRelatedContent(tag: String): List<RelatedContent> {

        val keyword = tag.lowercase()

        return when {

            keyword.contains("coldplay") -> coldplayContent

            keyword.contains("music") -> coldplayContent

            keyword.contains("spheres") -> coldplayContent

            keyword.contains("jakarta") -> coldplayContent

            keyword.contains("fix you") -> coldplayContent

            else -> emptyList()

        }

    }

    fun getRelatedHashtags(tag: String): List<String> {

        val keyword = tag.lowercase()

        return when {

            keyword.contains("coldplay") -> hashtags

            keyword.contains("music") -> hashtags

            keyword.contains("spheres") -> hashtags

            keyword.contains("jakarta") -> hashtags

            else -> emptyList()

        }

    }

}