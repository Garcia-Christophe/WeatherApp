package com.example.papameteo.domain.view

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.papameteo.R
import com.example.papameteo.domain.components.Screen

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val raw = if (isSystemInDarkTheme()) R.raw.logo_animation_dark else R.raw.logo_animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
    val progress by
    animateLottieCompositionAsState(composition = composition)

    LaunchedEffect(key1 = progress) {
        // end of animation
        if (progress == 1f) {
            navHostController.navigate(Screen.WeatherSearch.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun WeatherSplashScreenPreview() {
    SplashScreen(rememberNavController())
}