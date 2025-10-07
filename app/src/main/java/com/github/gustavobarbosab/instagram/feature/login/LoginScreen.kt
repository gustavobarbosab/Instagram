package com.github.gustavobarbosab.instagram.feature.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.github.gustavobarbosab.instagram.common.extensions.isNotNullOrBlank
import com.github.gustavobarbosab.instagram.core.navigation.Route
import com.github.gustavobarbosab.instagram.feature.home.HomeRoute
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
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNotNullOrBlank()) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = uiState.errorMessage.orEmpty(),
                    duration = SnackbarDuration.Short
                )
            }

        }
    }

    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            navController.popBackStack()
            navController.navigate(HomeRoute)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        LoginContent(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
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
}
