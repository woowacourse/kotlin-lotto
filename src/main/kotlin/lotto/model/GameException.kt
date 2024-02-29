package lotto.model

import lotto.model.user.UserEvent

class GameException(
    message: String,
    val event: UserEvent
) : Exception(message)
