package com.github.gustavobarbosab.instagram.feature.login

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.gustavobarbosab.instagram.R
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class LoginContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given an validation error on email field, should show an error`() {
        // Given
        val uiState = LoginUiState(
            emailError = "Invalid email"
        )

        // When
        composeTestRule.setContent {
            LoginContent(
                uiState = uiState,
                snackbarHostState = remember { SnackbarHostState() },
                onEmailChange = {},
                onPasswordChange = { },
                onLoginClick = {},
                onFacebookLoginClick = { },
                onForgotPasswordClick = { },
                onSignUpClick = { },
                modifier = Modifier
            )
        }

        // Then
        with(composeTestRule) {
            onNodeWithTag(
                LoginContentTestArgs.EMAIL_ICON,
                useUnmergedTree = true
            ).assertIsDisplayed()
            onNodeWithText("Invalid email").assertIsDisplayed()
        }
    }


    @Test
    fun `given a password change and button click, should call the listeners`() {
        // Given
        val uiState = LoginUiState()
        val onUsernameChanged = mockk<(String) -> Unit>(relaxed = true)
        val onPasswordChanged = mockk<(String) -> Unit>(relaxed = true)
        val onButtonClicked = mockk<() -> Unit>(relaxed = true)


        // When
        composeTestRule.setContent {
            LoginContent(
                uiState = uiState,
                snackbarHostState = remember { SnackbarHostState() },
                onEmailChange = onUsernameChanged,
                onPasswordChange = onPasswordChanged,
                onLoginClick = onButtonClicked,
                onFacebookLoginClick = { },
                onForgotPasswordClick = { },
                onSignUpClick = { },
                modifier = Modifier
            )
        }

        // Then
        with(composeTestRule) {
            R.string.login_username_label
            onNodeWithText(
                "username",
                substring = true,
                ignoreCase = true
            ).performTextInput("gus@aa.com")
            onNodeWithText("Password").performTextInput("123")
            onNodeWithText("Log In").performClick()
        }

        verify {
            onUsernameChanged("gus@aa.com")
            onPasswordChanged("123")
            onButtonClicked()
        }
    }
}