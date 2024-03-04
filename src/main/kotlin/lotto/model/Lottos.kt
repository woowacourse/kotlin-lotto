package lotto.model

import lotto.util.Constant

class Lottos {
    private var lottos: List<Lotto> = listOf()

    fun publishLottos(
        handpickedLottos: List<Lotto>,
        automaticLottos: List<Lotto>,
    ) {
        lottos = (handpickedLottos + automaticLottos)
    }

    fun calculateNumberOfLotto(purchaseAmount: Int): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }

    fun getLottos(): List<Lotto> = lottos
}
