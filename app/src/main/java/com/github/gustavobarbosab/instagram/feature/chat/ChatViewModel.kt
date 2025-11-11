package com.github.gustavobarbosab.instagram.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.gustavobarbosab.instagram.data.repository.ChatRepository
import com.github.gustavobarbosab.instagram.domain.model.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChatUiState(
    val messages: Set<ChatMessage> = emptySet(),
    val isConnected: Boolean = false,
    val isSending: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    // Mock user data - in production, this should come from user session
    private val currentUserId = "user_123"
    private val currentUserName = "John Doe"

    init {
        connectToChat()
        observeMessages()
    }

    private fun connectToChat() {
        viewModelScope.launch {
            try {
                chatRepository.connectToChat()
                _uiState.update { it.copy(isConnected = true, error = null) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Failed to connect: ${e.message}") }
            }
        }
    }

    private fun observeMessages() {
        viewModelScope.launch {
            chatRepository.observeMessages()
                .catch { e ->
                    _uiState.update { it.copy(error = "Error receiving messages: ${e.message}") }
                }
                .collect { message ->
                    _uiState.update {
                        it.copy(
                            messages = it.messages + message,
                            error = null
                        )
                    }
                }
        }
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isSending = true) }

            chatRepository.sendMessage(
                text = text,
                senderId = currentUserId,
                senderName = currentUserName
            ).onSuccess { message ->
                _uiState.update {
                    it.copy(
                        messages = it.messages + message,
                        isSending = false,
                        error = null
                    )
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        isSending = false,
                        error = "Failed to send message: ${e.message}"
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    override fun onCleared() {
        super.onCleared()
        chatRepository.disconnectFromChat()
    }
}
