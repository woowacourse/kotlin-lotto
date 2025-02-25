package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder

class LottoStore {
    fun sell(
        lottoMachine: LottoMachine,
        order: LottoOrder,
    ): List<Lotto> = lottoMachine.generate(order)
}
