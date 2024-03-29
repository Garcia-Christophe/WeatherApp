package com.example.papameteo.domain.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.papameteo.domain.view.SplashScreen
import com.example.papameteo.domain.view.WeatherDetailsListScreen
import com.example.papameteo.domain.view.WeatherSearchScreen

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(
                navHostController = navHostController
            )
        }
        composable(
            route = Screen.WeatherSearch.route
        ) {
            WeatherSearchScreen(
                navHostController = navHostController
            )
        }
        composable(
            route = "${Screen.WeatherList.route}/{cityName}",
            arguments = listOf(navArgument("cityName") { type = NavType.StringType })
        ) {
            WeatherDetailsListScreen(
                navHostController = navHostController,
                cityName = it.arguments?.getString("cityName") ?: ""
            )
        }
    }
}

sealed class Screen(val route: String) {
    object SplashScreen : Screen("Splash")
    object WeatherSearch : Screen("WeatherSearch")
    object WeatherList : Screen("WeatherList")
}