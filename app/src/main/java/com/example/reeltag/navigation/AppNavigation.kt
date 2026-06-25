package com.example.reeltag.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reeltag.ui.components.PlaceholderScreen
import com.example.reeltag.ui.instruction.InstructionScreen
import com.example.reeltag.ui.landing.LandingScreen
import com.example.reeltag.ui.reels.ReelsScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Landing.route
    ) {

        composable(Screen.Landing.route) {

            LandingScreen(

                onInstagramOriginalClick = {
                    navController.navigate(Screen.Instruction.route)
                },

                onReelTagClick = {
                    navController.navigate(Screen.Instruction.route)
                },

                onAnalysisClick = {
                    navController.navigate(Screen.Analysis.route)
                }

            )

        }

        composable(Screen.Instruction.route) {

            InstructionScreen(

                onStartClick = {
                    navController.navigate(Screen.Reels.route)
                }

            )

        }

        composable(Screen.Reels.route) {

            ReelsScreen(

                onCommentClick = {
                    navController.navigate(Screen.Comment.route)
                }

            )

        }

        composable(Screen.Comment.route) {

            PlaceholderScreen(
                title = "Comment Bottom Sheet"
            )

        }

        composable(Screen.Search.route) {

            PlaceholderScreen(
                title = "Search Screen"
            )

        }

        composable(Screen.Related.route) {

            PlaceholderScreen(
                title = "Related Content Screen"
            )

        }

        composable(Screen.Result.route) {

            PlaceholderScreen(
                title = "Result Screen"
            )

        }

        composable(Screen.Analysis.route) {

            PlaceholderScreen(
                title = "Analysis Screen"
            )

        }

    }

}