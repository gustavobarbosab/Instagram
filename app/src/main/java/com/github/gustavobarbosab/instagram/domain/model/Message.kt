package com.github.gustavobarbosab.instagram.domain.model

sealed interface Message

data class ButtonList(
    val buttons: List<Button>,
    val clickable: Boolean
) : Message {
    data class Button(
        val label: String,
        val action: Action?
    )
}

data class SingleSelection(
    val options: List<Option>,
    val analyticsParameterXPTO: String
) : Message {
    data class Option(
        val id: String,
        val label: String,
    )
}