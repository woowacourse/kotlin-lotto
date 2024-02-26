package lotto.domain

import lotto.domain.model.Lotto

interface LottoGenerator {
    fun make(): Lotto
}
