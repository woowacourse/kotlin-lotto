package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.Lotto.Companion.toLotto
import lotto.domain.model.LottoNumber.Companion.MAXIMUM_LOTTO_NUMBER
import lotto.domain.model.LottoNumber.Companion.MINIMUM_LOTTO_NUMBER

interface LottoGenerator {

    fun make(start: Int = MINIMUM_LOTTO_NUMBER, endInclusive: Int = MAXIMUM_LOTTO_NUMBER): Lotto {
        val numbers = (start..endInclusive).toList()
        return manipulate(numbers).toLotto()
    }

    fun manipulate(numbers: List<Int>): List<Int>
}
