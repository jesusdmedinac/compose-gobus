package com.jesusdmedinac.compose.gobus.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String = "",
    val type: String = "",
)
