package com.github.gustavobarbosab.instagram.data.mappers.message

import com.github.gustavobarbosab.instagram.domain.model.Message
import kotlinx.serialization.json.Json

interface MessageMapper {
    val serializer: Json
    fun map(data: String?): Message?
}
