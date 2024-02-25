package model

import util.Constant

class Lottos(private val purchaseAmount: Int) {
    private val lottos: List<Lotto> = publishLotto()

    fun getLottos(): List<Lotto> = lottos

    private fun publishLotto(): List<Lotto> {
        val numberOfLotto = calculateNumberOfLotto()
        return List(numberOfLotto) { LottoGenerator.generateLotto() }
    }

    private fun calculateNumberOfLotto(): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }

    override fun toString(): String {
        return lottos.joinToString("\n")
    }
}
