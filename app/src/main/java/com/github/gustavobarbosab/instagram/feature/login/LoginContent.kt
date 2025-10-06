package com.github.gustavobarbosab.instagram.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.instagram.common.ui.designsystem.button.PrimaryButton
import com.github.gustavobarbosab.instagram.common.ui.designsystem.button.TertiaryButton
import com.github.gustavobarbosab.instagram.common.ui.designsystem.textfield.InstagramTextField
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
import com.github.gustavobarbosab.instagram.common.ui.theme.InstagramTheme
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.customColors
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.sizing
import com.github.gustavobarbosab.instagram.common.ui.theme.composition.spacing
import com.github.gustavobarbosab.instagram.feature.login.components.DividerWithText
import com.github.gustavobarbosab.instagram.feature.login.components.SignUpPrompt
import com.github.gustavobarbosab.instagram.feature.login.components.SocialNetworkLoginButton

@Composable
fun LoginContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onFacebookLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            onNavigateToHome()
        }
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(MaterialTheme.sizing.size24),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space16)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Instagram",
                    fontFamily = FontFamily.Cursive,
                    style = MaterialTheme.typography.displayLarge.copy(
                        brush = MaterialTheme.customColors.gradient
                    )
                )

                Spacer(modifier = Modifier.weight(.4f))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(MaterialTheme.sizing.size24),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.space8)
                    ) {
                        InstagramTextField(
                            value = uiState.email,
                            onValueChange = onEmailChange,
                            placeholder = "Phone number, username, or email",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "Email",
                                )
                            },
                            keyboardType = KeyboardType.Email,
                            errorMessage = uiState.emailError
                        )

                        InstagramTextField(
                            value = uiState.password,
                            onValueChange = onPasswordChange,
                            placeholder = "Password",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Password",
                                )
                            },
                            trailingIcon = { toggle ->
                                Icon(
                                    imageVector = if (toggle) Icons.Default.CheckCircle else Icons.Default.AddCircle,
                                    contentDescription = if (toggle) "Gone" else "Visible",
                                )
                            },
                            defaultVisualTransformation = PasswordVisualTransformation(),
                            imeAction = ImeAction.Done,
                            errorMessage = uiState.passwordError
                        )

                        PrimaryButton(
                            onClick = onLoginClick,
                            enabled = !uiState.isLoading,
                            text = "Log In",
                            modifier = Modifier
                                .fillMaxWidth(),
                        )

                        TertiaryButton(
                            onClick = onForgotPasswordClick,
                            text = "Forgot your login details? Get help here.",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        DividerWithText("OR")

                        SocialNetworkLoginButton(
                            text = "Facebook",
                            onClick = onFacebookLoginClick
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                SignUpPrompt(
                    questionLabel = "Don't have an account?",
                    signUpLabel = "Sign up",
                    onSignUpClick = onSignUpClick
                )
            }
        }
    }
}

@ThemePreview
@Composable
private fun Preview() {
    val uiState = LoginUiState(
        email = "",
        password = "",
        isLoading = false,
        isLoginSuccessful = false,
        emailError = null,
        passwordError = null,
        errorMessage = null
    )

    InstagramTheme {
        LoginContent(
            uiState = uiState,
            onEmailChange = { },
            onPasswordChange = { },
            onLoginClick = { },
            onFacebookLoginClick = { },
            onForgotPasswordClick = { },
            onSignUpClick = { },
            onNavigateToHome = { },
        )
    }
}
