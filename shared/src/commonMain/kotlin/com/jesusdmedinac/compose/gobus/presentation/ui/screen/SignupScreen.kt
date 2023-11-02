package com.jesusdmedinac.compose.gobus.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.AndroidLottieView
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.IOSLottieView
import com.jesusdmedinac.compose.gobus.presentation.ui.composable.LottieAnimation
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignupScreenBehavior
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.SignupScreenState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    iosLottieEye: IOSLottieView,
    androidLottieEye: AndroidLottieView,
    signupScreenState: SignupScreenState,
    signupScreenBehavior: SignupScreenBehavior,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (signupScreenState.currentSignupStep) {
                            SignupScreenState.SignupStep.USER_TYPE -> "¿Viajas o manejas?"
                            SignupScreenState.SignupStep.USER_EMAIL -> "¿Cuál es tu correo electrónico?"
                            SignupScreenState.SignupStep.TRAVELER -> "Cuéntanos qué ruta sueles tomar cada día"
                            SignupScreenState.SignupStep.DRIVER -> "¿Cómo se llama la ruta que manejas?"
                            SignupScreenState.SignupStep.RESUME -> "¿Está todo correcto?"
                        },
                    )
                },
                navigationIcon = {
                    IconButton(onClick = signupScreenBehavior::onBackClicked) {
                        Image(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            when (signupScreenState.currentSignupStep) {
                SignupScreenState.SignupStep.RESUME -> Box(
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    FilledIconButton(
                        onClick = signupScreenBehavior::onNextClicked,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .clip(MaterialTheme.shapes.large),
                        enabled = signupScreenState.isNextButtonEnabled,
                    ) {
                        Text("Crear cuenta")
                    }
                }

                else -> FilledIconButton(
                    onClick = signupScreenBehavior::onNextClicked,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(MaterialTheme.shapes.large),
                    enabled = signupScreenState.isNextButtonEnabled,
                ) {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Forward",
                    )
                }
            }
        },
        floatingActionButtonPosition = when (signupScreenState.currentSignupStep) {
            SignupScreenState.SignupStep.RESUME -> FabPosition.Center

            else -> FabPosition.End
        },
    ) {
        when (signupScreenState.currentSignupStep) {
            SignupScreenState.SignupStep.USER_TYPE -> UserTypeSignupStep(
                modifier = Modifier.fillMaxSize().padding(vertical = 64.dp, horizontal = 32.dp),
                signupScreenState,
                signupScreenBehavior,
            )

            SignupScreenState.SignupStep.USER_EMAIL -> UserEmailSignupStep(
                modifier = Modifier.fillMaxSize().padding(vertical = 64.dp, horizontal = 32.dp),
                iosLottieEye = iosLottieEye,
                androidLottieEye = androidLottieEye,
                signupScreenState,
                signupScreenBehavior,
            )

            SignupScreenState.SignupStep.TRAVELER -> TravelerSignupStep(
                modifier = Modifier.fillMaxSize().padding(vertical = 64.dp, horizontal = 32.dp),
                signupScreenState,
                signupScreenBehavior,
            )

            SignupScreenState.SignupStep.DRIVER -> DriverSignupStep(
                modifier = Modifier.fillMaxSize().padding(vertical = 64.dp, horizontal = 32.dp),
                signupScreenState,
                signupScreenBehavior,
            )

            SignupScreenState.SignupStep.RESUME -> ResumeSignupStep(
                modifier = Modifier.fillMaxSize().padding(vertical = 64.dp, horizontal = 32.dp),
                iosLottieEye = iosLottieEye,
                androidLottieEye = androidLottieEye,
                signupScreenState,
                signupScreenBehavior,
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UserTypeSignupStep(
    modifier: Modifier = Modifier,
    signupScreenState: SignupScreenState,
    signupScreenBehavior: SignupScreenBehavior,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        listOf(
            SignupScreenState.UserType.TRAVELER,
            SignupScreenState.UserType.DRIVER,
        ).forEach { userType ->
            val isSelected = signupScreenState.selectedUserType == userType
            val cardWidthFraction by animateFloatAsState(targetValue = if (isSelected) 1f else 0.9f)
            val cardColorContainer by animateColorAsState(targetValue = if (isSelected) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.surface)
            val cardTextColor by animateColorAsState(
                targetValue = if (isSelected) {
                    contentColorFor(
                        MaterialTheme.colorScheme.surfaceTint,
                    )
                } else {
                    contentColorFor(MaterialTheme.colorScheme.surfaceVariant)
                },
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .animateContentSize(),
                contentAlignment = Alignment.Center,
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(cardWidthFraction)
                        .aspectRatio(1f),
                    onClick = {
                        signupScreenBehavior.onUserTypeSelected(userType)
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = cardColorContainer,
                        contentColor = cardTextColor,
                    ),
                ) {
                    val resourceName = when {
                        userType == SignupScreenState.UserType.TRAVELER -> "pasajero.xml"
                        else -> "conductor.xml"
                    }
                    val contentDescription = when {
                        userType == SignupScreenState.UserType.TRAVELER -> "Pasajero"
                        else -> "Conductor"
                    }
                    val label = when {
                        userType == SignupScreenState.UserType.TRAVELER -> "Soy Pasajero"
                        else -> "Soy Conductor"
                    }
                    Image(
                        painterResource(resourceName),
                        contentDescription = contentDescription,
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxWidth()
                            .background(cardColorContainer),
                    )
                    Text(
                        label,
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
        }
    }
}

@Composable
fun UserEmailSignupStep(
    modifier: Modifier = Modifier,
    iosLottieEye: IOSLottieView,
    androidLottieEye: AndroidLottieView,
    signupScreenState: SignupScreenState,
    signupScreenBehavior: SignupScreenBehavior,
) {
    var email by remember { mutableStateOf(signupScreenState.email) }
    var confirmEmail by remember { mutableStateOf(signupScreenState.confirmEmail) }
    var password by remember { mutableStateOf(signupScreenState.password) }
    var confirmPassword by remember { mutableStateOf(signupScreenState.confirmPassword) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        TextField(
            value = email,
            onValueChange = {
                email = it
                signupScreenBehavior.onEmailChange(it)
            },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
            ),
            isError = signupScreenState.isEmailError,
            supportingText = {
                AnimatedVisibility(
                    visible = signupScreenState.isEmailError,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Text(
                        "Quizás tu email no esté bien escrito, por favor revísalo",
                    )
                }
            },
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = confirmEmail,
            onValueChange = {
                confirmEmail = it
                signupScreenBehavior.onConfirmEmailChange(it)
            },
            label = { Text("Confirmar correo") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
            ),
            isError = signupScreenState.isConfirmEmailError,
            supportingText = {
                AnimatedVisibility(
                    visible = signupScreenState.isConfirmEmailError,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Text(
                        "Parece que tu correo no coincide, por favor revísalo",
                        modifier = Modifier.height(56.dp),
                    )
                }
            },
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
                signupScreenBehavior.onPasswordChange(it)
            },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            isError = signupScreenState.isPasswordError,
            supportingText = {
                AnimatedVisibility(
                    visible = signupScreenState.isPasswordError,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Text(
                        "Tu contraseña debe tener al menos 8 caracteres",
                    )
                }
            },
            visualTransformation = if (signupScreenState.isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = signupScreenBehavior::togglePasswordVisibility) {
                    LottieAnimation(
                        modifier = Modifier.size(24.dp),
                        iterations = 1,
                        progressRange = if (signupScreenState.isPasswordVisible) 0.5f to 1f else 0f to 0.5f,
                        iosLottieView = iosLottieEye,
                        androidLottieView = androidLottieEye,
                    )
                }
            },
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                signupScreenBehavior.onConfirmPasswordChange(it)
            },
            label = { Text("Confirmar contraseña") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            isError = signupScreenState.isConfirmPasswordError,
            supportingText = {
                AnimatedVisibility(
                    visible = signupScreenState.isConfirmPasswordError,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Text(
                        "Parece que tu contraseña no coincide, por favor revísala",
                        modifier = Modifier.height(56.dp),
                    )
                }
            },
            visualTransformation = if (signupScreenState.isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = signupScreenBehavior::togglePasswordVisibility) {
                    LottieAnimation(
                        modifier = Modifier.size(24.dp),
                        iterations = 1,
                        progressRange = if (signupScreenState.isPasswordVisible) 0.5f to 1f else 0f to 0.5f,
                        iosLottieView = iosLottieEye,
                        androidLottieView = androidLottieEye,
                    )
                }
            },
        )
    }
}

@Composable
fun TravelerSignupStep(
    modifier: Modifier = Modifier,
    signupScreenState: SignupScreenState,
    signupScreenBehavior: SignupScreenBehavior,
) {
    var path by remember { mutableStateOf(signupScreenState.path) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        TextField(
            value = path,
            onValueChange = {
                path = it
                signupScreenBehavior.onPathChange(it)
            },
            label = { Text("Ruta") },
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Composable
fun DriverSignupStep(
    modifier: Modifier = Modifier,
    signupScreenState: SignupScreenState,
    signupScreenBehavior: SignupScreenBehavior,
) {
    var path by remember { mutableStateOf(signupScreenState.path) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            "Entre más preciso seas será mejor para tus posibles pasajeros",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ejemplo: Pinos, Presa, 5 y 10", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = path,
            onValueChange = {
                path = it
                signupScreenBehavior.onPathChange(it)
            },
            label = { Text("Ruta") },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun ResumeSignupStep(
    modifier: Modifier,
    iosLottieEye: IOSLottieView,
    androidLottieEye: AndroidLottieView,
    signupScreenState: SignupScreenState,
    signupScreenBehavior: SignupScreenBehavior,
) {
    Column(
        modifier = modifier,
    ) {
        Text("Tipo de usuario")
        val userType = when (signupScreenState.selectedUserType) {
            SignupScreenState.UserType.TRAVELER -> "Soy pasajero"
            else -> "Soy conductor"
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("$userType")
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Correo electrónico")
        Spacer(modifier = Modifier.height(4.dp))
        Text("${signupScreenState.email}")
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Contraseña")
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "${signupScreenState.password}".let { password ->
                    if (signupScreenState.isPasswordVisible) {
                        password
                    } else {
                        password.map { '•' }.joinToString("")
                    }
                },
            )
            IconButton(onClick = signupScreenBehavior::togglePasswordVisibility) {
                LottieAnimation(
                    modifier = Modifier.size(24.dp),
                    iterations = 1,
                    progressRange = if (signupScreenState.isPasswordVisible) 0.5f to 1f else 0f to 0.5f,
                    iosLottieView = iosLottieEye,
                    androidLottieView = androidLottieEye,
                )
            }
        }
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Ruta")
        Spacer(modifier = Modifier.height(8.dp))
        Text("${signupScreenState.path}")
        Divider()
    }
}

@Composable
fun LottieEyeAnimation(
    isPasswordVisible: Boolean,
    iosLottieEye: IOSLottieView,
    androidLottieEye: AndroidLottieView,
) {
    LottieAnimation(
        modifier = Modifier.size(24.dp),
        iterations = 1,
        progressRange = if (isPasswordVisible) 0.5f to 1f else 1f to 0.5f,
        iosLottieView = iosLottieEye,
        androidLottieView = androidLottieEye,
    )
}
