package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount

class ManualLottoMachine : LottoMachine {
    override fun generate(
        lottoCount: LottoCount,
        lottoNumbers: List<List<Int>>?,
    ): List<Lotto> = lottoNumbers?.map { Lotto.of(it) } ?: emptyList()
}
