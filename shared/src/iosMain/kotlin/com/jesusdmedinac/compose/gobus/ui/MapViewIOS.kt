package com.jesusdmedinac.compose.gobus.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.MapKit.MKMapView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MapView(modifier: Modifier) {
    UIKitView(
        modifier = modifier,
        factory = {
            MKMapView()
        },
    )
}
