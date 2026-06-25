package com.example.reeltag.navigation

sealed class Screen(val route: String) {

    data object Landing : Screen("landing")

    data object Instruction : Screen("instruction")

    data object Reels : Screen("reels")

    data object Comment : Screen("comment")

    data object Search : Screen("search")

    data object Related : Screen("related")

    data object Result : Screen("result")

    data object Analysis : Screen("analysis")
}