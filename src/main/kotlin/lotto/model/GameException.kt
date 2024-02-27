package lotto.model

class GameException(
    message: String,
    val event: UserEvent
) : Exception(message)
