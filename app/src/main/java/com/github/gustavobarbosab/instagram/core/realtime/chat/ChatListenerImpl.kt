package com.github.gustavobarbosab.instagram.core.realtime.chat

import android.util.Log
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.channels.onFailure
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

private const val NORMAL_CLOSURE = 1000
private const val TAG = "WebSocket"

interface ChatListener {
    val subscribe: Channel<String>
}

class ChatListenerImpl : ChatListener, WebSocketListener() {

    override val subscribe: Channel<String> = Channel(
        capacity = BUFFERED,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override fun onOpen(
        webSocket: WebSocket,
        response: Response
    ) {
        Log.d(TAG, "Connected to WebSocket server")
        webSocket.send("Hello from Android!") // Example: send a message on open
    }

    override fun onMessage(
        webSocket: WebSocket,
        text: String
    ) {
        Log.d(TAG, "Message received: $text")
        // Process the received message
        subscribe
            .trySend(text)
            .onFailure {
                Log.e(TAG, "Error while sending the message: $it")
            }
    }

    override fun onClosing(
        webSocket: WebSocket,
        code: Int,
        reason: String
    ) {
        Log.d(TAG, "Closing: $code / $reason")
        webSocket.close(NORMAL_CLOSURE, null) // Close the connection gracefully
    }

    override fun onFailure(
        webSocket: WebSocket,
        t: Throwable,
        response: Response?
    ) {
        Log.e(TAG, "Error: ${t.message}", t)
        // Handle connection failures
    }
}