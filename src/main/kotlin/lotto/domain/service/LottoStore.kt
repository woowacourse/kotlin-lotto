package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount

class LottoStore {
    fun sell(
        lottoMachine: LottoMachine,
        lottoCount: LottoCount,
    ): List<Lotto> = lottoMachine.generate(lottoCount)
}
