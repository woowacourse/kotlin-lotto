package lotto.domain.service

import lotto.domain.model.Lotto

class LottoStore {
    fun sell(
        lottoMachine: LottoMachine,
        lottoCount: Int,
        lottoNumbers: List<List<Int>>? = null,
    ): List<Lotto> = lottoMachine.generate(lottoCount, lottoNumbers)
}
