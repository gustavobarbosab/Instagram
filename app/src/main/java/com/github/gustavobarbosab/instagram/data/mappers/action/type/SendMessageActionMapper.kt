package com.github.gustavobarbosab.instagram.data.mappers.action.type

import com.github.gustavobarbosab.instagram.data.mappers.action.ActionMapper
import com.github.gustavobarbosab.instagram.data.response.SendMessageActionResponse
import com.github.gustavobarbosab.instagram.domain.model.Action
import com.github.gustavobarbosab.instagram.domain.model.SendMessageAction
import kotlinx.serialization.json.Json

class SendMessageActionMapper(
    override val serializer: Json,
) : ActionMapper {
    override fun map(
        data: String?
    ): Action? = runCatching {
        data ?: return@runCatching null
        val action = serializer.decodeFromString<SendMessageActionResponse>(data)
        return@runCatching SendMessageAction(
            urlParam = action.urlParam
        )
    }.getOrNull()
}