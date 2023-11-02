package com.jesusdmedinac.compose.gobus.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.AndroidLottieView
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.IOSLottieView
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.LottieAnimation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    iosLottieView: IOSLottieView,
    androidLottieView: AndroidLottieView,
    navigateToLogin: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(3000)
        navigateToLogin()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            iterations = Int.MAX_VALUE,
            progressRange = 0f to 1f,
            iosLottieView,
            androidLottieView,
        )
    }
}
