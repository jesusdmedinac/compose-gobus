package com.jesusdmedinac.compose.gobus.presentation.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun LottieAnimation(
    modifier: Modifier,
    iterations: Int,
    progressRange: Pair<Float, Float>,
    iosLottieView: IOSLottieView,
    androidLottieView: AndroidLottieView
) {
    androidLottieView.viewAnimation(
        iterations = iterations,
        progressRange = progressRange,
    )
}
