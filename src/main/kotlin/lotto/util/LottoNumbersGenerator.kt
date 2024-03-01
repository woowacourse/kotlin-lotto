package lotto.util

import lotto.model.Lotto
import lotto.model.LottoNumber

object LottoNumbersGenerator : NumbersGenerator {
    override fun generateNumbers(count: Int): List<Set<Int>> =
        List(count) {
            LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).toSet()
        }
}
