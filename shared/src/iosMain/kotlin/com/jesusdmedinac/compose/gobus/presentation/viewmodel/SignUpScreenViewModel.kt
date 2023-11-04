package com.jesusdmedinac.compose.gobus.presentation.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container

actual abstract class SignUpScreenViewModel :
    CommonViewModel<SignUpScreenState, SignUpScreenSideEffect>,
    SignUpScreenIntents {
    actual override val scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined + Job())
    actual override val container: Container<SignUpScreenState, SignUpScreenSideEffect> =
        scope.container(SignUpScreenState())
}
