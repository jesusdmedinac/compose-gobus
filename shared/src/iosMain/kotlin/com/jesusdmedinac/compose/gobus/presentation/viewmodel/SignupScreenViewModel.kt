package com.jesusdmedinac.compose.gobus.presentation.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container

actual abstract class SignupScreenViewModel :
    CommonViewModel<SignupScreenState, SignupScreenSideEffect>,
    SignupScreenBehavior {
    actual override val scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined + Job())
    actual override val container: Container<SignupScreenState, SignupScreenSideEffect> =
        scope.container(SignupScreenState())
}
