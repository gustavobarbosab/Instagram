package com.github.gustavobarbosab.instagram.domain.model

interface Action

class SendMessageAction(
    val urlParam: String
) : Action

data class HandshakeAction(
    val handshakeId: String
) : Action