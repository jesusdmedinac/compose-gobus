package com.jesusdmedinac.compose.gobus.presentation.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun LottieAnimation(
    modifier: Modifier = Modifier,
    iterations: Int,
    progressRange: Pair<Float, Float>,
    iosLottieView: IOSLottieView = IOSLottieViewImpl,
    androidLottieView: AndroidLottieView = AndroidLottieViewImpl,
)

interface IOSLottieView {
    fun <T> viewAnimation(
        iterations: Int,
        progressRange: Pair<Float, Float>,
    ): T
}

interface AndroidLottieView {
    @Composable
    fun viewAnimation(
        iterations: Int,
        progressRange: Pair<Float, Float>,
    )
}

object IOSLottieViewImpl : IOSLottieView {
    override fun <T> viewAnimation(iterations: Int, progressRange: Pair<Float, Float>): T {
        TODO("Only implemented for iOS, ignore for Android")
    }
}

object AndroidLottieViewImpl : AndroidLottieView {
    @Composable
    override fun viewAnimation(iterations: Int, progressRange: Pair<Float, Float>) {
        TODO("Only implemented for Android, ignore for iOS")
    }
}
