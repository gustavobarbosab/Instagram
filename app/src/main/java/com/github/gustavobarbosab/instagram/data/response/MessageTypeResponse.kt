package com.github.gustavobarbosab.instagram.data.response

import kotlinx.serialization.Serializable

interface MessageTypeResponse

@Serializable
data class ButtonListMessageResponse(
    val buttons: List<Button>,
    val clickable: Boolean // parameter example
) : MessageTypeResponse {
    @Serializable
    data class Button(
        val label: String,
        val action: ActionResponse?
    )
}

@Serializable
data class SingleSelectionMessageResponse(
    val options: List<Option>,
    val analyticsParameterXPTO: String // parameter example
) : MessageTypeResponse {
    @Serializable
    data class Option(
        val id: String,
        val label: String,
    )
}