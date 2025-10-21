package com.github.gustavobarbosab.instagram.data.mappers.message.type

import com.github.gustavobarbosab.instagram.data.mappers.message.MessageMapper
import com.github.gustavobarbosab.instagram.data.response.SingleSelectionMessageResponse
import com.github.gustavobarbosab.instagram.domain.model.Message
import com.github.gustavobarbosab.instagram.domain.model.SingleSelection
import kotlinx.serialization.json.Json

class SingleSelectionMapper(
    override val serializer: Json,
) : MessageMapper {
    override fun map(
        data: String?
    ): Message? = runCatching {
        val data = data ?: return@runCatching null
        val singleSelection = serializer.decodeFromString<SingleSelectionMessageResponse>(data)
        return@runCatching SingleSelection(
            options = singleSelection.options.map {
                SingleSelection.Option(
                    id = it.id,
                    label = it.label
                )
            },
            analyticsParameterXPTO = singleSelection.analyticsParameterXPTO,
        )
    }.getOrNull()
}