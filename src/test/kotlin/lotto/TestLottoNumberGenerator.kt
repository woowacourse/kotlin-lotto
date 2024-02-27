package lotto

import lotto.model.Lotto
import lotto.model.NumberGenerator

class TestLottoNumberGenerator : NumberGenerator {
    override fun generate(
        size: Int,
        minNumber: Int,
        maxNumber: Int,
    ): Lotto = Lotto.lottoNumbersOf(listOf(1, 2, 3, 4, 5, 6))
}
