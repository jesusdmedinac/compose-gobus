package com.jesusdmedinac.compose.gobus.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jesusdmedinac.compose.gobus.ui.composable.CommonLottieView
import com.jesusdmedinac.compose.gobus.ui.screen.SplashScreen

@Composable
fun ComposeGobusApp(commonLottieView: CommonLottieView) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        SplashScreen(commonLottieView)
    }
}
