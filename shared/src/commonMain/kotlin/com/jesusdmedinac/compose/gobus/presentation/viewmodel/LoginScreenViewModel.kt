package com.jesusdmedinac.compose.gobus.presentation.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

expect abstract class LoginScreenViewModel() :
    CommonViewModel<LoginScreenState, LoginScreenSideEffect>,
    LoginScreenBehavior {
    override val scope: CoroutineScope
    override val container: Container<LoginScreenState, LoginScreenSideEffect>
}

class LoginScreenViewModelImpl : LoginScreenViewModel() {
    override fun login(): Job = intent {
        reduce {
            state.copy(isLoading = true)
        }
        delay(3000)
        reduce {
            state.copy(isLoading = false)
        }
    }

    override fun onEmailChanged(email: String): Job = intent {
        reduce {
            state.copy(email = email)
        }
    }

    override fun onPasswordChanged(password: String): Job = intent {
        reduce {
            state.copy(password = password)
        }
    }

    override fun navigateToSignup(): Job = intent {
        postSideEffect(LoginScreenSideEffect.NavigateToSignup)
        delay(500)
        postSideEffect(LoginScreenSideEffect.Idle)
    }
}

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
)

sealed class LoginScreenSideEffect {
    data object NavigateToSignup : LoginScreenSideEffect()
    data object Idle : LoginScreenSideEffect()
}

interface LoginScreenBehavior {
    fun login(): Job

    fun onEmailChanged(email: String): Job

    fun onPasswordChanged(password: String): Job

    fun navigateToSignup(): Job
}
