package lotto.model

import lotto.entity.Lotto

interface LottoGenerator {
    fun generate(): Lotto
}
