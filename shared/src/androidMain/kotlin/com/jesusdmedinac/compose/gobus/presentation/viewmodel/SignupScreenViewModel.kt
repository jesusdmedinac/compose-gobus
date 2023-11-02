package com.jesusdmedinac.compose.gobus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container

actual abstract class SignupScreenViewModel :
    ViewModel(),
    CommonViewModel<SignupScreenState, SignupScreenSideEffect>,
    SignupScreenBehavior {
    actual override val scope: CoroutineScope = viewModelScope
    actual override val container: Container<SignupScreenState, SignupScreenSideEffect> =
        scope.container(SignupScreenState())
}
