package com.jesusdmedinac.compose.gobus.presentation.viewmodel

import com.jesusdmedinac.compose.gobus.domain.model.User
import com.jesusdmedinac.compose.gobus.domain.usecase.SignUpUseCase
import com.jesusdmedinac.compose.gobus.utils.isEmailValid
import com.jesusdmedinac.compose.gobus.utils.sha256
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

expect abstract class SignUpScreenViewModel() :
    CommonViewModel<SignUpScreenState, SignUpScreenSideEffect>,
    SignUpScreenIntents {
    override val scope: CoroutineScope
    override val container: Container<SignUpScreenState, SignUpScreenSideEffect>
}

class SignUpScreenViewModelImpl(
    private val signUpUseCase: SignUpUseCase,
) : SignUpScreenViewModel() {
    override fun onUserTypeSelected(userType: SignUpScreenState.UserType) = intent {
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
            SignUpScreenState.SignupStep.USER_TYPE -> {
                postSideEffect(SignUpScreenSideEffect.NavigateToLogin)
                delay(500)
                postSideEffect(SignUpScreenSideEffect.Idle)
                return@intent
            }

            SignUpScreenState.SignupStep.USER_EMAIL -> SignUpScreenState.SignupStep.USER_TYPE

            SignUpScreenState.SignupStep.TRAVELER,
            SignUpScreenState.SignupStep.DRIVER,
            -> SignUpScreenState.SignupStep.USER_EMAIL

            SignUpScreenState.SignupStep.RESUME -> when (state.selectedUserType) {
                SignUpScreenState.UserType.TRAVELER -> SignUpScreenState.SignupStep.TRAVELER
                else -> SignUpScreenState.SignupStep.DRIVER
            }
        }
        reduce {
            state.copy(currentSignupStep = previousSignupType)
        }
    }

    override fun onNextClicked() = intent {
        val nextSignupType = when (state.currentSignupStep) {
            SignUpScreenState.SignupStep.USER_TYPE -> SignUpScreenState.SignupStep.USER_EMAIL
            SignUpScreenState.SignupStep.USER_EMAIL -> {
                when (state.selectedUserType) {
                    SignUpScreenState.UserType.TRAVELER -> SignUpScreenState.SignupStep.TRAVELER
                    else -> SignUpScreenState.SignupStep.DRIVER
                }
            }

            SignUpScreenState.SignupStep.TRAVELER,
            SignUpScreenState.SignupStep.DRIVER,
            -> SignUpScreenState.SignupStep.RESUME

            SignUpScreenState.SignupStep.RESUME -> {
                createAccount()
                return@intent
            }
        }
        reduce {
            state.copy(currentSignupStep = nextSignupType)
        }
    }

    private suspend fun SimpleSyntax<SignUpScreenState, SignUpScreenSideEffect>.createAccount() {
        val user = with(state) {
            val hashedPassword = sha256(password)
            User(
                email = email,
                type = selectedUserType.name,
                path = path,
                password = hashedPassword,
            )
        }
        signUpUseCase(user)
        postSideEffect(SignUpScreenSideEffect.NavigateToHome)
        delay(500)
        postSideEffect(SignUpScreenSideEffect.Idle)
        reduce { SignUpScreenState() }
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

data class SignUpScreenState(
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

    val readablePassword: String
        get() = if (isPasswordVisible) {
            password
        } else {
            password.map { '•' }.joinToString("")
        }
}

sealed class SignUpScreenSideEffect {
    data object Idle : SignUpScreenSideEffect()
    data object NavigateToLogin : SignUpScreenSideEffect()
    data object NavigateToHome : SignUpScreenSideEffect()
}

interface SignUpScreenIntents {
    fun onUserTypeSelected(userType: SignUpScreenState.UserType): Job
    fun onEmailChange(email: String): Job
    fun onConfirmEmailChange(email: String): Job
    fun onPasswordChange(password: String): Job
    fun onConfirmPasswordChange(password: String): Job
    fun onBackClicked(): Job
    fun onNextClicked(): Job
    fun onPathChange(path: String): Job
    fun togglePasswordVisibility(): Job
}
