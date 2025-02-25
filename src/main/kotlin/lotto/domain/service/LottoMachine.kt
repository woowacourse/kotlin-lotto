package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder

interface LottoMachine {
    fun generate(order: LottoOrder): List<Lotto>
}
