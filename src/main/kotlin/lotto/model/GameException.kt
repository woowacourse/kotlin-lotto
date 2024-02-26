package lotto.model

open class GameException(
    message: String,
    val event: UserEvent
) : Exception(message)
