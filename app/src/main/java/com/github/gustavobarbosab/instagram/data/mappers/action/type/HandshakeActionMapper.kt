package com.github.gustavobarbosab.instagram.data.mappers.action.type

import com.github.gustavobarbosab.instagram.data.mappers.action.ActionMapper
import com.github.gustavobarbosab.instagram.data.response.HandshakeActionResponse
import com.github.gustavobarbosab.instagram.domain.model.Action
import com.github.gustavobarbosab.instagram.domain.model.HandshakeAction
import kotlinx.serialization.json.Json

class HandshakeActionMapper(
    override val serializer: Json,
) : ActionMapper {
    override fun map(
        data: String?
    ): Action? = runCatching {
        data ?: return@runCatching null
        val handshakeAction = serializer.decodeFromString<HandshakeActionResponse>(data)
        return@runCatching HandshakeAction(
            handshakeId = handshakeAction.handshakeId
        )
    }.getOrNull()
}