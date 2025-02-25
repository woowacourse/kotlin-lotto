package lotto.domain.service

import lotto.domain.model.Lotto

class AutomaticLottoMachine : LottoMachine {
    override fun generate(
        count: Int,
        lottoNumbers: List<List<Int>>?,
    ): List<Lotto> = List(count) { Lotto.create() }
}
