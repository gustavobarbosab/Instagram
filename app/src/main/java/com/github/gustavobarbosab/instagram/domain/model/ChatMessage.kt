package com.github.gustavobarbosab.instagram.domain.model

data class ChatMessage(
    val id: String,
    val text: String,
    val senderId: String,
    val senderName: String,
    val timestamp: Long,
    val isFromCurrentUser: Boolean = false
)