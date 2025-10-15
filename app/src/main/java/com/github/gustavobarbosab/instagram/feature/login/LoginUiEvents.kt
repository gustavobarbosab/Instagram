package com.github.gustavobarbosab.instagram.feature.login

sealed class LoginUiEvents {
    data class FeedbackMessage(val message: String) : LoginUiEvents()
    data object LoginSuccessful : LoginUiEvents()
}