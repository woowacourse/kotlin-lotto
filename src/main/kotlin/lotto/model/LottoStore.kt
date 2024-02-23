package lotto.model

import lotto.service.LottoNumberGenerator
import lotto.service.RandomLottoNumberGenerator

class LottoStore private constructor(val lottos: List<Lotto>) {
    companion object {
        fun create(
            purchaseInfo: PurchaseInfo,
            lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator,
        ): LottoStore {
            val lottos = List(purchaseInfo.amount) { Lotto(lottoNumberGenerator.generate()) }
            return LottoStore(lottos)
        }
    }
}
