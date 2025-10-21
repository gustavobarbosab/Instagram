package com.github.gustavobarbosab.instagram.data.response

import kotlinx.serialization.Serializable

interface ActionTypeResponse

@Serializable
class SendMessageActionResponse(
    val urlParam: String
) : ActionTypeResponse

@Serializable
data class HandshakeActionResponse(
    val handshakeId: String
) : ActionTypeResponse