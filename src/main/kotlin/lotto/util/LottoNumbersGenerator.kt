package lotto.util

import lotto.model.Lotto
import lotto.model.LottoNumber

object LottoNumbersGenerator : NumbersGenerator {
    override fun generateNumbers(): List<Int> = LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT)
}
