package com.jesusdmedinac.compose.gobus.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.AndroidLottieView
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.IOSLottieView
import com.jesusdmedinac.compose.gobus.presentation.ui.screen.LoginScreen
import com.jesusdmedinac.compose.gobus.presentation.ui.screen.SignUpScreen
import com.jesusdmedinac.compose.gobus.presentation.ui.screen.SplashScreen
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenSideEffect
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenViewModel
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignUpScreenSideEffect
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignUpScreenViewModel

@Composable
fun ComposeGobusApp(
    iosLottieView: IOSLottieView,
    androidLottieView: AndroidLottieView,
    iosLottieEye: IOSLottieView,
    androidLottieEye: AndroidLottieView,
    loginScreenViewModel: LoginScreenViewModel,
    signupScreenViewModel: SignUpScreenViewModel,
) {
    var currentRoute by remember { mutableStateOf<Route>(Route.SplashScreen) }

    when (currentRoute) {
        Route.HomeScreen -> {
            Text("HomeScreen")
        }

        Route.LoginScreen -> {
            val loginScreenState by loginScreenViewModel
                .container
                .stateFlow
                .collectAsState()
            val loginScreenSideEffect by loginScreenViewModel
                .container
                .sideEffectFlow
                .collectAsState(LoginScreenSideEffect.Idle)
            LaunchedEffect(loginScreenSideEffect) {
                when (loginScreenSideEffect) {
                    LoginScreenSideEffect.Idle -> Unit
                    LoginScreenSideEffect.NavigateToSignup -> {
                        currentRoute = Route.SignupScreen
                    }
                }
            }

            LoginScreen(
                loginScreenState,
                loginScreenViewModel,
            )
        }

        Route.SignupScreen -> {
            val signupScreenState by signupScreenViewModel
                .container
                .stateFlow
                .collectAsState()
            val signupScreenSideEffect by signupScreenViewModel
                .container
                .sideEffectFlow
                .collectAsState(SignUpScreenSideEffect.Idle)

            LaunchedEffect(signupScreenSideEffect) {
                when (signupScreenSideEffect) {
                    SignUpScreenSideEffect.Idle -> Unit

                    SignUpScreenSideEffect.NavigateToLogin -> {
                        currentRoute = Route.LoginScreen
                    }

                    SignUpScreenSideEffect.NavigateToHome -> {
                        currentRoute = Route.HomeScreen
                    }
                }
            }

            SignUpScreen(
                iosLottieEye = iosLottieEye,
                androidLottieEye = androidLottieEye,
                signupScreenState,
                signupScreenViewModel,
            )
        }

        Route.SplashScreen -> {
            SplashScreen(
                iosLottieView,
                androidLottieView,
            ) {
                currentRoute = Route.LoginScreen
            }
        }
    }
}

sealed class Route {
    data object SplashScreen : Route()
    data object LoginScreen : Route()
    data object SignupScreen : Route()
    data object HomeScreen : Route()
}
