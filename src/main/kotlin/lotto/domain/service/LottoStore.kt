package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount

class LottoStore {
    fun publish(
        lottoMachine: LottoMachine,
        lottoCount: LottoCount,
        lottoNumbers: List<List<Int>>? = null,
    ): List<Lotto> = lottoMachine.generate(lottoCount, lottoNumbers)
}
