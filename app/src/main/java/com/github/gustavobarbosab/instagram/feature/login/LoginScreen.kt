package com.github.gustavobarbosab.instagram.feature.login

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.github.gustavobarbosab.instagram.common.extensions.isNotNullOrBlank
import com.github.gustavobarbosab.instagram.core.navigation.Route
import com.github.gustavobarbosab.instagram.feature.home.HomeRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute : Route {
    override val name: String
        get() = "Login"
}

@Composable
fun LoginScreen(
    navController: NavHostController,
    onNavigateToSignUp: () -> Unit = {},
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is LoginUiEvents.FeedbackMessage -> snackbarHostState.showSnackbar(
                    message = event.message,
                    duration = SnackbarDuration.Short
                )

                is LoginUiEvents.LoginSuccessful -> {
                    Log.d("Teste", "Aguardando ${event.delay}ms para redirecionar")
                    delay(event.delay)
                    navController.popBackStack()
                    navController.navigate(HomeRoute)
                }
            }
        }
    }

    LoginContent(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onLoginClick = {
            keyboardController?.hide()
            viewModel.login()
        },
        onFacebookLoginClick = viewModel::loginWithFacebook,
        onForgotPasswordClick = viewModel::forgotPassword,
        onSignUpClick = onNavigateToSignUp,
    )
}
