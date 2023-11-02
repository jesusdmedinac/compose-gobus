package com.jesusdmedinac.compose.gobus.presentation.viewmodel

import com.jesusdmedinac.compose.gobus.utils.isEmailValid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

expect abstract class SignupScreenViewModel() :
    CommonViewModel<SignupScreenState, SignupScreenSideEffect>,
    SignupScreenBehavior {
    override val scope: CoroutineScope
    override val container: Container<SignupScreenState, SignupScreenSideEffect>
}

class SignupScreenViewModelImpl : SignupScreenViewModel() {
    override fun onUserTypeSelected(userType: SignupScreenState.UserType) = intent {
        reduce {
            state.copy(selectedUserType = userType)
        }
    }

    override fun onEmailChange(email: String): Job = intent {
        reduce {
            val isEmailError = !isEmailValid(email)
            state.copy(email = email, isEmailError = isEmailError)
        }
    }

    override fun onConfirmEmailChange(email: String): Job = intent {
        reduce {
            val isConfirmEmailError = email != state.email
            state.copy(confirmEmail = email, isConfirmEmailError = isConfirmEmailError)
        }
    }

    override fun onPasswordChange(password: String): Job = intent {
        reduce {
            val isPasswordError = password.length < 8
            state.copy(password = password, isPasswordError = isPasswordError)
        }
    }

    override fun onConfirmPasswordChange(password: String): Job = intent {
        reduce {
            val isConfirmPasswordError = password != state.password
            state.copy(confirmPassword = password, isConfirmPasswordError = isConfirmPasswordError)
        }
    }

    override fun onBackClicked(): Job = intent {
        val previousSignupType = when (state.currentSignupStep) {
            SignupScreenState.SignupStep.USER_TYPE -> {
                postSideEffect(SignupScreenSideEffect.NavigateToLogin)
                delay(500)
                postSideEffect(SignupScreenSideEffect.Idle)
                return@intent
            }

            SignupScreenState.SignupStep.USER_EMAIL -> SignupScreenState.SignupStep.USER_TYPE

            SignupScreenState.SignupStep.TRAVELER,
            SignupScreenState.SignupStep.DRIVER,
            -> SignupScreenState.SignupStep.USER_EMAIL

            SignupScreenState.SignupStep.RESUME -> when (state.selectedUserType) {
                SignupScreenState.UserType.TRAVELER -> SignupScreenState.SignupStep.TRAVELER
                else -> SignupScreenState.SignupStep.DRIVER
            }
        }
        reduce {
            state.copy(currentSignupStep = previousSignupType)
        }
    }

    override fun onNextClicked() = intent {
        val nextSignupType = when (state.currentSignupStep) {
            SignupScreenState.SignupStep.USER_TYPE -> SignupScreenState.SignupStep.USER_EMAIL
            SignupScreenState.SignupStep.USER_EMAIL -> {
                when (state.selectedUserType) {
                    SignupScreenState.UserType.TRAVELER -> SignupScreenState.SignupStep.TRAVELER
                    else -> SignupScreenState.SignupStep.DRIVER
                }
            }

            SignupScreenState.SignupStep.TRAVELER,
            SignupScreenState.SignupStep.DRIVER,
            -> SignupScreenState.SignupStep.RESUME

            SignupScreenState.SignupStep.RESUME -> {
                postSideEffect(SignupScreenSideEffect.CreateAccount)
                delay(500)
                postSideEffect(SignupScreenSideEffect.Idle)
                return@intent
            }
        }
        reduce {
            state.copy(currentSignupStep = nextSignupType)
        }
    }

    override fun onPathChange(path: String): Job = intent {
        reduce {
            state.copy(path = path)
        }
    }

    override fun togglePasswordVisibility(): Job = intent {
        reduce {
            state.copy(isPasswordVisible = !state.isPasswordVisible)
        }
    }
}

data class SignupScreenState(
    val selectedUserType: UserType = UserType.UNDEFINED,
    val currentSignupStep: SignupStep = SignupStep.USER_TYPE,
    val email: String = "",
    val isEmailError: Boolean = false,
    val confirmEmail: String = "",
    val isConfirmEmailError: Boolean = false,
    val password: String = "",
    val isPasswordError: Boolean = false,
    val confirmPassword: String = "",
    val isConfirmPasswordError: Boolean = false,
    val path: String = "",
    val isPasswordVisible: Boolean = false,
) {
    enum class UserType {
        TRAVELER,
        DRIVER,
        UNDEFINED,
    }

    enum class SignupStep {
        USER_TYPE,
        USER_EMAIL,
        TRAVELER,
        DRIVER,
        RESUME,
    }

    val isNextButtonEnabled: Boolean
        get() = when (currentSignupStep) {
            SignupStep.USER_TYPE -> selectedUserType != UserType.UNDEFINED
            SignupStep.USER_EMAIL -> email.isNotBlank()
            SignupStep.TRAVELER,
            SignupStep.DRIVER,
            -> path.isNotBlank()

            SignupStep.RESUME -> true
        }
}

sealed class SignupScreenSideEffect {
    data object Idle : SignupScreenSideEffect()
    data object NavigateToLogin : SignupScreenSideEffect()
    data object CreateAccount : SignupScreenSideEffect()
}

interface SignupScreenBehavior {
    fun onUserTypeSelected(userType: SignupScreenState.UserType): Job
    fun onEmailChange(email: String): Job
    fun onConfirmEmailChange(email: String): Job
    fun onPasswordChange(password: String): Job
    fun onConfirmPasswordChange(password: String): Job
    fun onBackClicked(): Job
    fun onNextClicked(): Job
    fun onPathChange(path: String): Job
    fun togglePasswordVisibility(): Job
}
