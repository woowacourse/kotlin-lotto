package domain.service

import domain.model.Lotto
import domain.model.PurchasePrice

class FakeLottoNumberGenerator(private val fixedNumbers: List<Lotto>) : LottoGenerator {
    override fun generate(quickPickLottoAmount: Int): List<Lotto> {
        return fixedNumbers
    }

    override fun validateMoney(money: PurchasePrice) {}
}
