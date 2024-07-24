package com.arzhang.borutoapp.presentation.screens.splash

import android.content.res.Configuration
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arzhang.borutoapp.R
import com.arzhang.borutoapp.navigation.Screen
import com.arzhang.borutoapp.ui.theme.Purple40
import com.arzhang.borutoapp.ui.theme.Purple500
import com.arzhang.borutoapp.ui.theme.Purple700

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val rotate = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        rotate.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if(onBoardingCompleted) {
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }
    }
    Splash(degrees = rotate.value)
}

@Composable
fun Splash(degrees: Float) {
    var backGroundColor = listOf(Purple700, Purple500)
    if(isSystemInDarkTheme()) {
        backGroundColor = listOf(Color.Black, Color.DarkGray)
    }
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(backGroundColor))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    Splash(degrees = 0f)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SplashScreenDarkPreview() {
    Splash(degrees = 0f)
}