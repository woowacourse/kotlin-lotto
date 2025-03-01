package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber

class LottoMaker {
    fun autoLottoMaker(count: Int): List<Lotto> = List<Lotto>(count) { Lotto.createRandom() }

    private fun manualLottoMaker(numberList: List<LottoNumber>): Lotto? = Lotto.createOrNull(numberList)
}
