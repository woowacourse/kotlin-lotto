package lotto.domain.model

import lotto.domain.service.RandomLottoGenerator
import lotto.domain.value.LottoPayInfo

class LottoMachine {
    private val randomLottoGenerator = RandomLottoGenerator()

    fun generateLottos(payInfo: LottoPayInfo): Lottos {
        val tickets = List(payInfo.getLottoPurchaseQuantity()) { randomLottoGenerator.generateLotto() }
        return Lottos(tickets)
    }
}
