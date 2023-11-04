package com.jesusdmedinac.compose.gobus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container

actual abstract class SignUpScreenViewModel :
    ViewModel(),
    CommonViewModel<SignUpScreenState, SignUpScreenSideEffect>,
    SignUpScreenIntents {
    actual override val scope: CoroutineScope = viewModelScope
    actual override val container: Container<SignUpScreenState, SignUpScreenSideEffect> =
        scope.container(SignUpScreenState())
}
