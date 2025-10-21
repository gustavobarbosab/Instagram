package com.github.gustavobarbosab.instagram.data.mappers.action

import com.github.gustavobarbosab.instagram.data.response.ActionResponse
import com.github.gustavobarbosab.instagram.domain.model.Action

class ActionMapperHandler(
    private val actionMappers: Map<String, ActionMapper>,
) {
    fun map(actionResponse: ActionResponse?): Action? {
        actionResponse ?: return null
        return actionMappers[actionResponse.type]?.map(actionResponse.data)
    }
}