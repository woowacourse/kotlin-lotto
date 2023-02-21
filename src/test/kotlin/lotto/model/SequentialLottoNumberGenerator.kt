package lotto.model

import lotto.entity.Lotto

class SequentialLottoNumberGenerator(private val numbers: List<Lotto>) : LottoGenerator {
    private val index: Int = 0
    override fun generate(): Lotto = numbers[index]
}
