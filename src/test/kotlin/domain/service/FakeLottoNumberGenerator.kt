package domain.service

import domain.model.Lotto

class FakeLottoNumberGenerator(private val fixedNumbers: List<Lotto>) : LottoGenerator {
    override fun generate(quickPickLottoAmount: Int): List<Lotto> {
        return fixedNumbers
    }
}
