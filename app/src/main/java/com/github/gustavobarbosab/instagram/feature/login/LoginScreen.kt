package com.github.gustavobarbosab.instagram.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LoginContent(
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
        onNavigateToHome = onNavigateToHome
    )
}
