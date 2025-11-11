package com.github.gustavobarbosab.instagram.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageResponse(
    val id: String,
    val text: String,
    val senderId: String,
    val senderName: String,
    val timestamp: Long
)