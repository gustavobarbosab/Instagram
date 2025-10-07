package com.github.gustavobarbosab.instagram.feature.login

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.github.gustavobarbosab.instagram.R
import com.github.gustavobarbosab.instagram.common.ui.designsystem.components.button.PrimaryButton
import com.github.gustavobarbosab.instagram.common.ui.designsystem.components.button.TertiaryButton
import com.github.gustavobarbosab.instagram.common.ui.designsystem.components.textfield.InstagramTextField
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.InstagramTheme
import com.github.gustavobarbosab.instagram.common.ui.designsystem.theme.composition.customColors
import com.github.gustavobarbosab.instagram.common.ui.preview.ThemePreview
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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
                    placeholder = stringResource(R.string.login_username_label),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = stringResource(R.string.login_username_icon_description),
                        )
                    },
                    keyboardType = KeyboardType.Email,
                    errorMessage = uiState.emailError
                )

                InstagramTextField(
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    placeholder = stringResource(R.string.login_password_label),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = stringResource(R.string.login_password_label),
                        )
                    },
                    trailingIcon = { toggle ->
                        Icon(
                            imageVector = if (toggle) {
                                Icons.Default.CheckCircle
                            } else {
                                Icons.Default.AddCircle
                            },
                            contentDescription = if (toggle) {
                                stringResource(R.string.login_password_hidden)
                            } else stringResource(
                                R.string.login_password_visible
                            ),
                        )
                    },
                    defaultVisualTransformation = PasswordVisualTransformation(),
                    imeAction = ImeAction.Done,
                    errorMessage = uiState.passwordError
                )

                PrimaryButton(
                    onClick = onLoginClick,
                    enabled = !uiState.isLoading,
                    text = stringResource(R.string.login_button),
                    modifier = Modifier.fillMaxWidth(),
                )

                TertiaryButton(
                    onClick = onForgotPasswordClick,
                    text = stringResource(R.string.login_forgot_password),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                DividerWithText("OR")

                SocialNetworkLoginButton(
                    text = stringResource(R.string.login_facebook_method),
                    onClick = onFacebookLoginClick
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.space16))

        SignUpPrompt(
            questionLabel = stringResource(R.string.login_sign_up_question),
            signUpLabel = stringResource(R.string.login_sign_up),
            onSignUpClick = onSignUpClick
        )
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
        )
    }
}
