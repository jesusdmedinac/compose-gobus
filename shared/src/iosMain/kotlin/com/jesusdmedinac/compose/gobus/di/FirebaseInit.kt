package com.jesusdmedinac.compose.gobus.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.initialize

actual fun initFirebase(context: Any?): FirebaseApp = Firebase.initialize()
    ?: throw IllegalStateException("FirebaseApp is null")
