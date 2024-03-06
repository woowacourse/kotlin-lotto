package lotto.domain

import lotto.domain.model.Lotto

class ManualLottoGenerator(private val lottoNumber: List<Int>) : LottoGenerator {
    override fun make() = Lotto.from(lottoNumber)
}
