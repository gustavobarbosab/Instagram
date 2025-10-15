package com.github.gustavobarbosab.instagram.feature.login

sealed class LoginUiEvents {
    data class FeedbackMessage(val message: String) : LoginUiEvents()
    data class LoginSuccessful(val delay: Long) : LoginUiEvents()
}