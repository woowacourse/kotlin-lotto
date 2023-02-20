package domain

import domain.lotto.LottoBundle
import domain.lotto.generator.LottoVendingMachine

class Purchaser(val spentMoney: Money) {
    val purchasedLottoCount: Int = (spentMoney / LOTTO_PRICE).toInt()
    val purchasedLottoBundle: LottoBundle = LottoVendingMachine.getLottoBundle(purchasedLottoCount)

    companion object {
        private val LOTTO_PRICE: Money = Money(1000)
    }
}
