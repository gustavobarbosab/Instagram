package com.github.gustavobarbosab.instagram.data.repository

import com.github.gustavobarbosab.instagram.core.realtime.chat.ChatConnectionManager
import com.github.gustavobarbosab.instagram.core.realtime.chat.ChatListener
import com.github.gustavobarbosab.instagram.data.mappers.ChatMessageMapper
import com.github.gustavobarbosab.instagram.data.request.SendMessageRequest
import com.github.gustavobarbosab.instagram.data.service.ChatApiService
import com.github.gustavobarbosab.instagram.domain.model.ChatMessage
import com.github.gustavobarbosab.instagram.data.response.ChatMessageResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.json.Json

interface ChatRepository {
    fun connectToChat()
    fun disconnectFromChat()
    fun observeMessages(): Flow<ChatMessage>
    suspend fun sendMessage(text: String, senderId: String, senderName: String): Result<ChatMessage>
}

class ChatRepositoryImpl(
    private val chatConnectionManager: ChatConnectionManager,
    private val chatListener: ChatListener,
    private val chatApiService: ChatApiService,
    private val currentUserId: String = "current_user_id", // This should come from user session
    private val json: Json = Json { ignoreUnknownKeys = true }
) : ChatRepository {

    override fun connectToChat() {
        chatConnectionManager.connect()
    }

    override fun disconnectFromChat() {
        chatConnectionManager.disconnect()
    }

    override fun observeMessages(): Flow<ChatMessage> = flow {
        chatListener.subscribe.receiveAsFlow().collect { messageJson ->
            try {
                val response = json.decodeFromString<ChatMessageResponse>(messageJson)
                val chatMessage = ChatMessageMapper.toDomain(response, currentUserId)
                emit(chatMessage)
            } catch (e: Exception) {
                // Log error but continue listening
                e.printStackTrace()
            }
        }
    }

    override suspend fun sendMessage(
        text: String,
        senderId: String,
        senderName: String
    ): Result<ChatMessage> {
        return try {
            val request = SendMessageRequest(
                text = text,
                senderId = senderId,
                senderName = senderName,
                timestamp = System.currentTimeMillis()
            )

            val response = chatApiService.sendMessage(request)

            if (response.isSuccessful && response.body() != null) {
                val chatMessage = ChatMessageMapper.toDomain(response.body()!!, currentUserId)
                Result.success(chatMessage)
            } else {
                Result.failure(Exception("Failed to send message: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
