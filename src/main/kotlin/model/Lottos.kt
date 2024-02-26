package model

import util.Constant

class Lottos(private val purchaseAmount: Int, private val handpickedLottos: List<Lotto>) {
    init {
        require(purchaseAmount % Constant.PURCHASE_AMOUNT_UNIT == 0)
    }

    private val lottos: List<Lotto> = publishLottos()

    fun getLottos(): List<Lotto> = lottos

    fun getHandPickedLottos(): List<Lotto> = handpickedLottos

    private fun publishLottos(): List<Lotto> {
        val numberOfLotto = calculateNumberOfLotto()
        val automaticLottos = List(numberOfLotto - handpickedLottos.size) { LottoGenerator.generateLotto() }
        return handpickedLottos + automaticLottos
    }

    private fun calculateNumberOfLotto(): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }

    override fun toString(): String {
        return lottos.joinToString("\n")
    }
}
