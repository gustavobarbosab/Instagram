package com.github.gustavobarbosab.instagram.feature.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val LOGIN_STATE = "LOGIN_STATE"
private const val LOGIN_SUCCESS = "LOGIN_SUCCESS"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val uiState: StateFlow<LoginUiState> = savedStateHandle.getStateFlow(
        LOGIN_STATE,
        LoginUiState()
    )

    private val _uiEvent = MutableSharedFlow<LoginUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun updateEmail(email: String) {
        savedStateHandle[LOGIN_STATE] = uiState.value.copy(
            email = email,
            emailError = null,
        )
    }

    fun updatePassword(password: String) {
        savedStateHandle[LOGIN_STATE] = uiState.value.copy(
            password = password,
            passwordError = null,
        )
    }

    fun initScreen() {
        val isLoggedIn = savedStateHandle.get<Boolean>(LOGIN_SUCCESS)
        if (isLoggedIn == true) {
            viewModelScope.launch {
                _uiEvent.emit(LoginUiEvents.LoginSuccessful(delay = 0))
            }
        }
    }

    fun login() {
        val currentState = uiState.value

        // Validate form
        val emailError = validateEmail(currentState.email)
        val passwordError = validatePassword(currentState.password)

        if (emailError != null || passwordError != null) {
            savedStateHandle[LOGIN_STATE] = currentState.copy(
                emailError = emailError,
                passwordError = passwordError
            )
            return
        }

        // Start loading
        savedStateHandle[LOGIN_STATE] = currentState.copy(
            isLoading = true,
        )

        viewModelScope.launch {
            try {
                // Simulate network call
                Log.d("Teste", "1 = Iniciando pagamento")
                delay(3000)
                Log.d("Teste", "2 = recebou sucesso no pagamento")

                // Mock login logic - replace with actual API call
                val loginSuccess = performLogin(currentState.email, currentState.password)

                if (loginSuccess) {
                    savedStateHandle[LOGIN_STATE] = uiState.value.copy(
                        isLoading = false,
                    )
                    _uiEvent.emit(LoginUiEvents.LoginSuccessful(delay = 4000))
                    savedStateHandle[LOGIN_SUCCESS] = true
                    Log.d("Teste", "3 = pagou com sucesso")
                } else {
                    savedStateHandle[LOGIN_STATE] = uiState.value.copy(
                        isLoading = false,
                    )
                    _uiEvent.emit(
                        LoginUiEvents.FeedbackMessage(
                            "Invalid email or password. Please try again."
                        )
                    )
                    Log.d("Teste", "3 = Falha no pagamento")
                }
            } catch (e: Exception) {
                savedStateHandle[LOGIN_STATE] = uiState.value.copy(
                    isLoading = false,
                )
                _uiEvent.emit(
                    LoginUiEvents.FeedbackMessage(
                        "Login failed. Please check your connection and try again."
                    )
                )
                Log.d("Teste", "3 = Erro $e")
            }
        }
    }

    fun loginWithFacebook() {
        savedStateHandle[LOGIN_STATE] = uiState.value.copy(
            isLoading = true,
        )

        viewModelScope.launch {
            try {
                delay(1500)

                val facebookLoginSuccess = performFacebookLogin()

                if (facebookLoginSuccess) {
                    savedStateHandle[LOGIN_STATE] = uiState.value.copy(
                        isLoading = false,
                    )
                    _uiEvent.emit(LoginUiEvents.LoginSuccessful(delay = 4000))
                    savedStateHandle[LOGIN_SUCCESS] = true
                } else {
                    savedStateHandle[LOGIN_STATE] = uiState.value.copy(
                        isLoading = false,
                    )

                }
            } catch (e: Exception) {
                savedStateHandle[LOGIN_STATE] = uiState.value.copy(
                    isLoading = false,
                )
                _uiEvent.emit(
                    LoginUiEvents.FeedbackMessage(
                        "Facebook login failed. Please try again."
                    )
                )
            }
        }
    }

    fun forgotPassword() {
        // Navigate to forgot password screen or show dialog
        // For now, just show a message
        viewModelScope.launch {
            _uiEvent.emit(
                LoginUiEvents.FeedbackMessage(
                    "Forgot password functionality not implemented yet."
                )
            )
        }
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
