package com.github.gustavobarbosab.instagram.data

import com.github.gustavobarbosab.instagram.data.mappers.action.ActionMapperHandler
import com.github.gustavobarbosab.instagram.data.mappers.action.type.HandshakeActionMapper
import com.github.gustavobarbosab.instagram.data.mappers.action.type.SendMessageActionMapper
import com.github.gustavobarbosab.instagram.data.mappers.message.MessageMapperHandler
import com.github.gustavobarbosab.instagram.data.mappers.message.type.ButtonListMapper
import com.github.gustavobarbosab.instagram.data.mappers.message.type.SingleSelectionMapper
import com.github.gustavobarbosab.instagram.data.response.MessageResponse
import com.github.gustavobarbosab.instagram.domain.model.ButtonList
import com.github.gustavobarbosab.instagram.domain.model.HandshakeAction
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class DataUsageExample {

//{
//  "type": "BUTTON_LIST",
//  "data": {
//    "clickable": true,
//    "buttons": [
//      {
//        "label": "Teste botão",
//        "action": {
//          "type": "HANDSHAKE_ACTION",
//          "data": {
//            "handshakeId": "ID123123"
//          }
//        }
//      }
//    ],
//  }
//}

    @Test
    fun testingFlow() {
        val actionMapper = mapOf(
            "HANDSHAKE_ACTION" to HandshakeActionMapper(Json),
            "SEND_MESSAGE_ACTION" to SendMessageActionMapper(Json),
        )
        val actionHandlerMapper = ActionMapperHandler(actionMapper)

        // No dagger será declarado algo do tipo @MapKey("BUTTON_LIST" to ButtonListMapper::class.java)
        // Dagger criara esse dicionário de mappers baseado no tipo de mensagem
        val messageMappers = mapOf(
            "BUTTON_LIST" to ButtonListMapper(Json, actionHandlerMapper),
            "SINGLE_SELECT" to SingleSelectionMapper(Json)
        )

        // ele passará isso para o nosso manipulador de mensagens
        val messageMapperHandler = MessageMapperHandler(messageMappers)

        // essa seria uma resposta vinda do BE para mensagem de botão
        val response =
            "{\n\"type\":\"BUTTON_LIST\",\n\"data\":\"{\\n\\\"buttons\\\":[\\n{\\n\\\"label\\\":\\\"Teste botão\\\",\\n\\\"action\\\":{\\n\\\"type\\\":\\\"HANDSHAKE_ACTION\\\",\\n\\\"data\\\":\\\"{\\\\n\\\\\\\"handshakeId\\\\\\\":\\\\\\\"ID123123\\\\\\\"\\\\n}\\\"\\n}\\n}\\n],\\n\\\"clickable\\\":true\\n}\"\n}"

        // simulando o parser disso para uma MessageResponse
        val parsedMessage = Json.decodeFromString<MessageResponse>(response)

        // após o parser teremos algo desse tipo
        //    val message = MessageResponse(
        //        type = "BUTTON_LIST",
        //        data = "{    \"label\": \"Teste botão\",    \"action\": {      \"type\": \"SEND_MESSAGE\"    }  }"
        //    )


        // Chamar o nosso mapper de mensagens
        val returned = messageMapperHandler.mapFrom(parsedMessage)

        // Após conversão devemos ter
        val expected = ButtonList(
            buttons = listOf(
                ButtonList.Button(
                    label = "Teste botão",
                    action = HandshakeAction("ID123123")
                ),
            ),
            clickable = true,
        )

        println("Are equal: ${expected == returned}")
        println("Expected value: $expected")
        println("Returned value: $returned")
        assertEquals(expected, returned)
    }
}