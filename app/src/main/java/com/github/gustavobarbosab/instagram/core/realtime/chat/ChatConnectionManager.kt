package com.github.gustavobarbosab.instagram.core.realtime.chat

import com.github.gustavobarbosab.instagram.core.realtime.WebSocketCloseCode
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

private const val BASE_URL = "ws://10.0.2.2:8080/realtime-events"

class ChatConnectionManager(
    private val listener: WebSocketListener,
    private val client: OkHttpClient = OkHttpClient()
) {

    private var webSocket: WebSocket? = null

    fun connect() {
        val request = Request
            .Builder()
            .url(BASE_URL)
            .header("username", "sdjadasdas")
            .build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun disconnect() {
        webSocket?.close(WebSocketCloseCode.NormalClosure.code, null)
    }
}