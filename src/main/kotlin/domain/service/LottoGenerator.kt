package domain.service

import domain.model.Lotto
import domain.model.PurchasePrice

interface LottoGenerator {
    fun generate(money: PurchasePrice): List<Lotto>

    companion object {
        const val STANDARD_AMOUNT_UNIT = 1000
        const val STANDARD_AMOUNT_UNIT_KOREAN = "천원"
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
    }
}
