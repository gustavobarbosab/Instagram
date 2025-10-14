package com.github.gustavobarbosab.instagram.feature.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUiState(
    // Atributos UI
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,

    // Single actions
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false,
) : Parcelable


// Problema Checkout
// Usuário clicou para finalizar o pagamento
// Foi no whatsapp responder o namorado
// Recebemos a resposta de pagamento com sucesso
// O Android matou nosso app para preservar RAM
// O usuário volta no app para ver se deu certo o pagamento

// Checkout -> Sucesso -> Navega para confirmação
// SharedFlow só emite o valor uma vez, no caso de navegação se você não coletou já era.
