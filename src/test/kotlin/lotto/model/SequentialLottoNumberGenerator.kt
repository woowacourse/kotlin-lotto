package lotto.model

import lotto.entity.Game
import lotto.entity.Lotto

class SequentialLottoNumberGenerator(private val game: Game) : LottoGenerator {
    private val index: Int = 0
    override fun generate(): Lotto = game.value[index]
}
