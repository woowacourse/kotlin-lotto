package util

import domain.model.Lotto
import domain.model.LottoNumber

class LottoFactory {
    fun lottoOf(vararg numbers: Int): Lotto {
        return Lotto(numbers.map { LottoNumber(it) })
    }

    fun extractionNumber(lotto: Lotto): List<Int> {
        return lotto.numbers.map { it.number }
    }
}
