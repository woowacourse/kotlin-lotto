package lotto.model

import lotto.entity.Lotto

class SequentialLottoNumberGenerator(private val lottos: List<Lotto>) : LottoGenerator {
    private var index: Int = 0
    override fun generate(): Lotto = lottos[index++]
}
