package com.github.gustavobarbosab.instagram.data.mappers.message.type

import com.github.gustavobarbosab.instagram.data.mappers.action.ActionMapperHandler
import com.github.gustavobarbosab.instagram.data.mappers.message.MessageMapper
import com.github.gustavobarbosab.instagram.data.response.ButtonListMessageResponse
import com.github.gustavobarbosab.instagram.domain.model.ButtonList
import com.github.gustavobarbosab.instagram.domain.model.Message
import kotlinx.serialization.json.Json

class ButtonListMapper(
    override val serializer: Json,
    private val actionMapperHandler: ActionMapperHandler
) : MessageMapper {
    override fun map(
        data: String?
    ): Message? {
        data ?: return null
        val buttonResponse = serializer.decodeFromString<ButtonListMessageResponse>(data)
        return ButtonList(
            buttons = buttonResponse.buttons.map {
                ButtonList.Button(
                    label = it.label,
                    action = actionMapperHandler.map(it.action)
                )
            },
            clickable = buttonResponse.clickable,
        )
    }
}

