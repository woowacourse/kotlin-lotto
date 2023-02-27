package lotto.model

import lotto.entity.Lotto

class SequentialLottoNumberGenerator(private val numbers: List<Lotto>) : LottoGenerator {
    private var index: Int = 0
    override fun generate(): Lotto = numbers[index++]
}
