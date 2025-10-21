package com.github.gustavobarbosab.instagram.data.response

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val type: String,
    val data: String?
)