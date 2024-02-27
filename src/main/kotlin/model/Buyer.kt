package model

import util.LottoConstants

class Buyer(private val purchaseAmount: Int) {
    val numberOfLotto: Int = calculateNumberOfLotto()
    var purchasedLotto: Lottos = Lottos(emptyList())

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / LottoConstants.UNIT_PRICE
    }

    fun buyLottos(lottos: Lottos) {
        purchasedLotto = purchasedLotto.copy(purchasedLotto.publishedLottos + lottos.publishedLottos)
    }
}
