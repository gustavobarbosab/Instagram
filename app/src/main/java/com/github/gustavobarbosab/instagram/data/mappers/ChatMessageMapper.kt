package com.github.gustavobarbosab.instagram.data.mappers

import com.github.gustavobarbosab.instagram.data.response.ChatMessageResponse
import com.github.gustavobarbosab.instagram.domain.model.ChatMessage

object ChatMessageMapper {

    fun toDomain(response: ChatMessageResponse, currentUserId: String): ChatMessage {
        return ChatMessage(
            id = response.id,
            text = response.text,
            senderId = response.senderId,
            senderName = response.senderName,
            timestamp = response.timestamp,
            isFromCurrentUser = response.senderId == currentUserId
        )
    }
}
