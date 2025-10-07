package com.github.gustavobarbosab.instagram.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null,
            errorMessage = null
        )
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = null,
            errorMessage = null
        )
    }

    fun login() {
        val currentState = _uiState.value

        // Validate form
        val emailError = validateEmail(currentState.email)
        val passwordError = validatePassword(currentState.password)

        if (emailError != null || passwordError != null) {
            _uiState.value = currentState.copy(
                emailError = emailError,
                passwordError = passwordError
            )
            return
        }

        // Start loading
        _uiState.value = currentState.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                // Simulate network call
                delay(2000)

                // Mock login logic - replace with actual API call
                val loginSuccess = performLogin(currentState.email, currentState.password)

                if (loginSuccess) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoginSuccessful = true,
                        errorMessage = null
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Invalid email or password. Please try again."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Login failed. Please check your connection and try again."
                )
            }
        }
    }

    fun loginWithFacebook() {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                delay(1500)

                val facebookLoginSuccess = performFacebookLogin()

                if (facebookLoginSuccess) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoginSuccessful = true,
                        errorMessage = null
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Facebook login failed. Please try again."
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Facebook login failed. Please try again."
                )
            }
        }
    }

    fun forgotPassword() {
        // Navigate to forgot password screen or show dialog
        // For now, just show a message
        _uiState.value = _uiState.value.copy(
            errorMessage = "Forgot password functionality not implemented yet."
        )
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email is required"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> "Please enter a valid email address"

            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password is required"
            password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }

    private fun performLogin(email: String, password: String): Boolean {
        // Mock login - replace with actual API call
        return email.isNotEmpty() && password.isNotEmpty() && password.length >= 6 && password == "123456"
    }

    private fun performFacebookLogin(): Boolean {
        // Mock Facebook login - replace with actual Facebook SDK integration
        return true
    }
}