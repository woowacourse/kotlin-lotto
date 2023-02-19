package domain

import domain.lotto.LottoBundle
import domain.lotto.generator.LottoVendingMachine

class Purchaser(val purchasedMoney: Money) {
    val numberOfPurchasedLotto: Int = (purchasedMoney / LOTTO_PRICE).toInt()
    val purchasedLottoBundle: LottoBundle = LottoVendingMachine.getLottoBundle(numberOfPurchasedLotto)

    companion object {
        private val LOTTO_PRICE: Money = Money(1000)
    }
}
