package com.jesusdmedinac.compose.gobus.utils

expect fun platform(): Platform

sealed interface Platform {
    data object Android : Platform
    data object IOS : Platform
}
