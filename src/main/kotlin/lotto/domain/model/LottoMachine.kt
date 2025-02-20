package lotto.domain.model

import lotto.domain.service.LottoGenerator
import lotto.domain.value.PurchaseQuantity

class LottoMachine {
    private val lottoGenerator = LottoGenerator()

    fun generateLottos(purchaseQuantity: PurchaseQuantity): List<Lotto> {
        val lottos = List(purchaseQuantity.quantity) { lottoGenerator.generateLotto() }
        return lottos
    }
}
