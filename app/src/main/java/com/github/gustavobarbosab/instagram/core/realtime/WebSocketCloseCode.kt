package com.github.gustavobarbosab.instagram.core.realtime

sealed class WebSocketCloseCode(val code: Int) {

    // Normal closure
    data object NormalClosure : WebSocketCloseCode(1000)
    data object GoingAway : WebSocketCloseCode(1001)
    data object ProtocolError : WebSocketCloseCode(1002)
    data object UnsupportedData : WebSocketCloseCode(1003)

    // Application closure
    data object NoStatusReceived : WebSocketCloseCode(1005)
    data object AbnormalClosure : WebSocketCloseCode(1006)

    // Payload closure
    data object InvalidFramePayload : WebSocketCloseCode(1007)
    data object PolicyViolation : WebSocketCloseCode(1008)
    data object MessageTooBig : WebSocketCloseCode(1009)
    data object MissingExtension : WebSocketCloseCode(1010)
    data object InternalError : WebSocketCloseCode(1011)
    data object ServiceRestart : WebSocketCloseCode(1012)
    data object TryAgainLater : WebSocketCloseCode(1013)

    // Future usage
    data object TLSHandshakeFailed : WebSocketCloseCode(1015)

    companion object {
        fun from(code: Int): WebSocketCloseCode? =
            when (code) {
                1000 -> NormalClosure
                1001 -> GoingAway
                1002 -> ProtocolError
                1003 -> UnsupportedData
                1005 -> NoStatusReceived
                1006 -> AbnormalClosure
                1007 -> InvalidFramePayload
                1008 -> PolicyViolation
                1009 -> MessageTooBig
                1010 -> MissingExtension
                1011 -> InternalError
                1012 -> ServiceRestart
                1013 -> TryAgainLater
                1015 -> TLSHandshakeFailed
                else -> null
            }
    }
}
