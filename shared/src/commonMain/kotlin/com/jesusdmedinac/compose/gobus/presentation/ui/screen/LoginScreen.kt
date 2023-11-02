package com.jesusdmedinac.compose.gobus.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenBehavior
import com.jesusdmedinac.compose.gobus.presentation.viewmodel.LoginScreenState

@Composable
fun LoginScreen(
    loginScreenState: LoginScreenState,
    loginScreenBehavior: LoginScreenBehavior,
) {
    var email by remember { mutableStateOf(loginScreenState.email) }
    var password by remember { mutableStateOf(loginScreenState.password) }
    val isLoading = loginScreenState.isLoading

    Scaffold(
        modifier = Modifier.padding(horizontal = 32.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Inicio de sesión",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.size(64.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        loginScreenBehavior.onEmailChanged(it)
                    },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.size(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        loginScreenBehavior.onPasswordChanged(it)
                    },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.size(32.dp))
                Button(
                    onClick = {
                        loginScreenBehavior.login()
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    } else {
                        Text("Iniciar sesión")
                    }
                }
                Spacer(modifier = Modifier.size(8.dp))
                TextButton(
                    onClick = {
                        loginScreenBehavior.navigateToSignup()
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Registrarse")
                }
            }
        }
    }
}
