package com.jesusdmedinac.compose.gobus.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LottieView(modifier: Modifier) {
    UIKitView(
        modifier = modifier,
        factory = {
            UIView()
        },
    )
}
