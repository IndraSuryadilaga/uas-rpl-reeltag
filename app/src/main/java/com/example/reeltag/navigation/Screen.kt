package com.example.reeltag.navigation

sealed class Screen(val route: String) {

    data object Landing : Screen("landing")

    data object Instruction : Screen("instruction")

    data object Reels : Screen("reels")

    data object Comment : Screen("comment")

    data object Search : Screen("search")

    object Related : Screen("related/{tag}") {

        fun createRoute(tag: String): String {
            return "related/$tag"
        }

    }

    data object Result : Screen("result")

    data object Analysis : Screen("analysis")
}