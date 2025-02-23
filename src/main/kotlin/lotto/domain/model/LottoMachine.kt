package lotto.domain.model

import lotto.domain.value.LottoPayInfo

class LottoMachine {
    fun generateLottos(payInfo: LottoPayInfo): Lottos {
        val tickets = List(payInfo.getLottoPurchaseQuantity()) { Lotto.createRandom() }
        return Lottos(tickets)
    }
}
