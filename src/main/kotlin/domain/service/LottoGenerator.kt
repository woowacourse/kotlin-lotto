package domain.service

import domain.model.Lotto
import domain.model.PurchasePrice

interface LottoGenerator {
    fun generate(quickPickLottoAmount: Int): List<Lotto>

    fun validateMoney(money: PurchasePrice)

    companion object {
        const val STANDARD_AMOUNT_UNIT = 1000
    }
}
