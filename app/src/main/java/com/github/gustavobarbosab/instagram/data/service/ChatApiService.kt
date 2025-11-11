package com.github.gustavobarbosab.instagram.data.service

import com.github.gustavobarbosab.instagram.data.request.SendMessageRequest
import com.github.gustavobarbosab.instagram.data.response.ChatMessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApiService {
    @POST("messages")
    suspend fun sendMessage(@Body message: SendMessageRequest): Response<ChatMessageResponse>
}
