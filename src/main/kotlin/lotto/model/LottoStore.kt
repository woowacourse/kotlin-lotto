package lotto.model

import lotto.service.LottoNumberGenerator
import lotto.service.RandomLottoNumberGenerator

class LottoStore(
    purchaseQuantity: PurchaseQuantity,
    lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator,
) {
    val lottos: List<Lotto>

    init {
        val tmpLottos = mutableListOf<Lotto>()
        repeat(purchaseQuantity.amount) {
            val lotto = Lotto(lottoNumberGenerator.generate())
            tmpLottos.add(lotto)
        }
        lottos = tmpLottos
    }
}
