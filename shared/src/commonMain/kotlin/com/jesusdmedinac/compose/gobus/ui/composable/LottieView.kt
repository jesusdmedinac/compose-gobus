package com.jesusdmedinac.compose.gobus.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun LottieView(modifier: Modifier = Modifier, commonLottieView: CommonLottieView)

interface CommonLottieView {
    fun <T> viewAnimation(): T
}
