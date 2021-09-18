package com.ruben.epicworld.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ruben.epicworld.presentation.Destinations.GameDetails
import com.ruben.epicworld.presentation.Destinations.GameDetailsArgs.GameId
import com.ruben.epicworld.presentation.Destinations.GameVideos
import com.ruben.epicworld.presentation.Destinations.GameVideosArgs.GameIdVideo
import com.ruben.epicworld.presentation.Destinations.Home
import com.ruben.epicworld.presentation.Destinations.Search
import com.ruben.epicworld.presentation.details.GameDetailsScreen
import com.ruben.epicworld.presentation.home.ui.HomeScreen
import com.ruben.epicworld.presentation.search.GameSearchScreen
import com.ruben.epicworld.presentation.videos.GameVideosScreen

/**
 * Created by Ruben Quadros on 05/08/21
 **/
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun EpicWorldApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    NavHost(navController = navController, startDestination = Home) {
        composable(Home) {
            HomeScreen(
                openSearch = actions.openSearch,
                openFilters = actions.openFilter,
                openGameDetails = actions.openGameDetails
            )
        }
        composable(
            "$GameDetails/{$GameId}",
            arguments = listOf(
                navArgument(GameId) { type = NavType.IntType }
            )
        ) {
            GameDetailsScreen(
                gameId = it.arguments?.getInt(GameId) ?: 0,
                navigateBack = actions.navigateBack,
                openGameTrailer =  actions.openGameVideos
            )
        }
        composable(
            "$GameVideos/{${GameIdVideo}}",
            arguments = listOf(
                navArgument(GameIdVideo) { type = NavType.IntType }
            )
        ) {
            GameVideosScreen(
                gameId = it.arguments?.getInt(GameIdVideo) ?: 0,
                navigateBack = actions.navigateBack
            )
        }
        composable(Search) {
            GameSearchScreen(navigateBack = actions.navigateBack)
        }
    }
}