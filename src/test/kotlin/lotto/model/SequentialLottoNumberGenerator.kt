package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoGame

class SequentialLottoNumberGenerator(private val lottos: List<Lotto>) : LottoGenerator {
    private val index: Int = 0
    override fun generate(): Lotto = lottos[index]
}
