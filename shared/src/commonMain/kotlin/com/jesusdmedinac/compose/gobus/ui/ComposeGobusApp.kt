package com.jesusdmedinac.compose.gobus.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ComposeGobusApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        MapView(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
