package lotto.model

sealed class GameState {
    object Play : GameState()

    object End : GameState()
}
