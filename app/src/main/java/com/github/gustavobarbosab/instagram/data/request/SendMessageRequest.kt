package com.github.gustavobarbosab.instagram.data.request

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageRequest(
    val text: String,
    val senderId: String,
    val senderName: String,
    val timestamp: Long
)