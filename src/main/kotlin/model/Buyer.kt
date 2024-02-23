package model

import util.Constant

class Buyer(private val purchaseAmount: Int) {
    val numberOfLotto: Int = calculateNumberOfLotto()
    lateinit var lottos: Lottos

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }

    fun buyLottos(lottos: Lottos) {
        this.lottos = lottos
    }
}
