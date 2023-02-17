package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoGame

class SequentialLottoNumberGenerator(private val lottoGame: LottoGame) : LottoGenerator {
    private val index: Int = 0
    override fun generate(): Lotto = lottoGame.value[index]
}
