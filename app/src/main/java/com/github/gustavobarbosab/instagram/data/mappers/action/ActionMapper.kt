package com.github.gustavobarbosab.instagram.data.mappers.action

import com.github.gustavobarbosab.instagram.domain.model.Action
import kotlinx.serialization.json.Json

interface ActionMapper {
    val serializer: Json
    fun map(data: String?): Action?
}