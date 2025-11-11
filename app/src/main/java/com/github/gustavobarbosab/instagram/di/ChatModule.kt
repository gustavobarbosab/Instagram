package com.github.gustavobarbosab.instagram.di

import com.github.gustavobarbosab.instagram.core.realtime.chat.ChatConnectionManager
import com.github.gustavobarbosab.instagram.core.realtime.chat.ChatListener
import com.github.gustavobarbosab.instagram.core.realtime.chat.ChatListenerImpl
import com.github.gustavobarbosab.instagram.data.repository.ChatRepository
import com.github.gustavobarbosab.instagram.data.repository.ChatRepositoryImpl
import com.github.gustavobarbosab.instagram.data.service.ChatApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }

    @Provides
    @Singleton
    fun provideChatListener(): ChatListener {
        return ChatListenerImpl()
    }

    @Provides
    @Singleton
    fun provideChatConnectionManager(
        chatListener: ChatListener,
        okHttpClient: OkHttpClient
    ): ChatConnectionManager {
        return ChatConnectionManager(
            listener = chatListener as okhttp3.WebSocketListener,
            client = okHttpClient
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        // Use 10.0.2.2 for Android emulator to access host machine
        // Use your computer's IP address if testing on a physical device
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideChatApiService(retrofit: Retrofit): ChatApiService {
        return retrofit.create(ChatApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideChatRepository(
        chatConnectionManager: ChatConnectionManager,
        chatListener: ChatListener,
        chatApiService: ChatApiService,
        json: Json
    ): ChatRepository {
        return ChatRepositoryImpl(
            chatConnectionManager = chatConnectionManager,
            chatListener = chatListener,
            chatApiService = chatApiService,
            json = json
        )
    }
}
