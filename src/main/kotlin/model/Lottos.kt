package model

import util.Constant

class Lottos(private val purchaseAmount: Int, private val numberOfHandpickedLotto: Int) {
    init {
        require(purchaseAmount % Constant.PURCHASE_AMOUNT_UNIT == 0)
    }

    private val lottos: List<Lotto> = publishLottos()

    fun getLottos(): List<Lotto> = lottos

    fun getNumberOfHandpickedLotto(): Int = numberOfHandpickedLotto

    private fun publishLottos(): List<Lotto> {
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
