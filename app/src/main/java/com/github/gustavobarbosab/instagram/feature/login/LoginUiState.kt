package com.github.gustavobarbosab.instagram.feature.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUiState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
) : Parcelable