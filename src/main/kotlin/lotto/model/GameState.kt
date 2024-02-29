package lotto.model

sealed class GameState {
    data object Play : GameState()

    data object End : GameState()
}
