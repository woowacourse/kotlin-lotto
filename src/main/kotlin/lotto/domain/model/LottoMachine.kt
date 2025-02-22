package lotto.domain.model

import lotto.domain.service.LottoGenerator
import lotto.domain.value.LottoPayInfo

class LottoMachine {
    private val lottoGenerator = LottoGenerator()

    fun generateLottos(payInfo: LottoPayInfo): Lottos {
        val tickets = List(payInfo.getLottoPurchaseQuantity()) { lottoGenerator.generateLotto() }
        return Lottos(tickets)
    }
}
