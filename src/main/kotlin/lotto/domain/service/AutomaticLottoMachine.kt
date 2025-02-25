package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount

class AutomaticLottoMachine : LottoMachine {
    override fun generate(
        lottoCount: LottoCount,
        lottoNumbers: List<List<Int>>?,
    ): List<Lotto> = List(lottoCount.count) { Lotto.create() }
}
