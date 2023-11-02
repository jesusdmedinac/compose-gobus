package com.jesusdmedinac.compose.gobus.presentation.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LottieAnimation(
    modifier: Modifier,
    iterations: Int,
    progressRange: Pair<Float, Float>,
    iosLottieView: IOSLottieView,
    androidLottieView: AndroidLottieView,
) {
    UIKitView(
        modifier = modifier,
        factory = {
            iosLottieView.viewAnimation(
                iterations = iterations,
                progressRange = progressRange,
            )
        },
    )
}
