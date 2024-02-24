package model

import util.Constant

class Buyer(private val purchaseAmount: Int) {
    val numberOfLotto: Int = calculateNumberOfLotto()
    var purchasedLotto: Lottos = Lottos(emptyList())

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }

    fun buyLottos(lottos: Lottos) {
        purchasedLotto = purchasedLotto.copy(purchasedLotto.publishedLottos + lottos.publishedLottos)
    }
}
