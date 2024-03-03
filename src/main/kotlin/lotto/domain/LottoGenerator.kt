package lotto.domain

import lotto.domain.model.Lotto

interface LottoGenerator {
    fun make(minimumInclusive: Int, maximumInclusive: Int): Lotto
}
