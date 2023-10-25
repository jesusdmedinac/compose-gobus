package com.jesusdmedinac.compose.gobus.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jesusdmedinac.compose.gobus.ui.composable.CommonLottieView
import com.jesusdmedinac.compose.gobus.ui.composable.LottieView

@Composable
fun SplashScreen(commonLottieView: CommonLottieView) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LottieView(modifier = Modifier.fillMaxSize(), commonLottieView)
    }
}
