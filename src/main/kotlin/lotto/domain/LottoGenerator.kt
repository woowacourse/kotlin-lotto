package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.Lotto.Companion.toLotto

interface LottoGenerator {

    fun make(start: Int = MINIMUM_LOTTO_NUMBER, endInclusive: Int = MAXIMUM_LOTTO_NUMBER): Lotto {
        val numbers = (start..endInclusive).toList()
        return manipulate(numbers).toLotto()
    }

    fun manipulate(numbers: List<Int>): List<Int>

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
    }
}
