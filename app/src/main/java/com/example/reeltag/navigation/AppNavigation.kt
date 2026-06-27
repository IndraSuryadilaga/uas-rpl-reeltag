package com.example.reeltag.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reeltag.ui.components.PlaceholderScreen
import com.example.reeltag.ui.instruction.InstructionScreen
import com.example.reeltag.ui.landing.LandingScreen
import com.example.reeltag.ui.reels.ReelsScreen
import com.example.reeltag.ui.related.RelatedScreen
import com.example.reeltag.ui.search.SearchScreen
import com.example.reeltag.util.UsabilitySessionManager

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

                    UsabilitySessionManager.startOriginalSession()

                    navController.navigate(Screen.Instruction.route)

                },

                onReelTagClick = {

                    UsabilitySessionManager.startReelTagSession()

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

                onTagClick = { tag ->

                    navController.navigate(
                        Screen.Related.createRoute(tag)
                    )

                },

                onSearchClick = {

                    navController.navigate(Screen.Search.route)

                }

            )

        }

        composable(Screen.Comment.route) {

            PlaceholderScreen(
                title = "Comment Bottom Sheet"
            )

        }

        composable(
            route = Screen.Related.route
        ) { backStackEntry ->

            val tag =
                backStackEntry.arguments?.getString("tag") ?: ""

            RelatedScreen(

                title = "Related Content",

                selectedTag = tag,

                showCloseButton = true,

                onClose = {
                    navController.popBackStack()
                },

                onBack = {

                    navController.popBackStack()

                }

            )

        }

        composable(Screen.Search.route) {

            SearchScreen(

                onBack = {

                    navController.popBackStack()

                },

                onReelsClick = {

                    navController.navigate(Screen.Reels.route) {
                        popUpTo(Screen.Reels.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }

                }

            )

        }

        composable(Screen.Analysis.route) {

            PlaceholderScreen(
                title = "Analysis Screen"
            )

        }

    }

}