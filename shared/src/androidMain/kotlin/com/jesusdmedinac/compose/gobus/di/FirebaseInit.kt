package com.jesusdmedinac.compose.gobus.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize

actual fun initFirebase(context: Any?): FirebaseApp = Firebase.initialize(
    context,
    options = FirebaseOptions(
        applicationId = "1:892734723976:android:a2b808271cd8bcb6c9cc3d",
        apiKey = "AIzaSyBiy0w7wz_KIKrv3nAV4o37z0lh6WhUHvk",
        projectId = "gobus-260206",
    ),
)
