package com.github.gustavobarbosab.instagram.data.mappers.message

import com.github.gustavobarbosab.instagram.data.response.MessageResponse
import com.github.gustavobarbosab.instagram.domain.model.Message

class MessageMapperHandler(
    val messageMappers: Map<String, MessageMapper>
) {
    fun mapFrom(response: MessageResponse): Message? {
        val mapper = messageMappers[response.type]
        return mapper?.map(response.data)
    }
}