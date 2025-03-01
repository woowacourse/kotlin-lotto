package lotto.domain.service

import lotto.domain.model.Lotto

interface LottoMachine {
    fun generate(): Lotto
}
