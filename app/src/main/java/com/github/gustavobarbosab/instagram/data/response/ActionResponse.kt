package com.github.gustavobarbosab.instagram.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ActionResponse(
    val type: String,
    val data: String?
)