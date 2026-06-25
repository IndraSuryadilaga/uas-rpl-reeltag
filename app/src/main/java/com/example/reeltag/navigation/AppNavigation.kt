package com.example.reeltag.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reeltag.ui.components.PlaceholderScreen
import com.example.reeltag.ui.landing.LandingScreen

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

            PlaceholderScreen("Instruction Screen")

        }

        composable(Screen.Reels.route) {

            PlaceholderScreen("Reels Screen")

        }

        composable(Screen.Comment.route) {

            PlaceholderScreen("Comment Screen")

        }

        composable(Screen.Search.route) {

            PlaceholderScreen("Search Screen")

        }

        composable(Screen.Related.route) {

            PlaceholderScreen("Related Content Screen")

        }

        composable(Screen.Result.route) {

            PlaceholderScreen("Result Screen")

        }

        composable(Screen.Analysis.route) {

            PlaceholderScreen("Analysis Screen")

        }

    }

}