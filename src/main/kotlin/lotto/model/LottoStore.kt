package lotto.model

import lotto.service.LottoNumberGenerator
import lotto.service.RandomLottoNumberGenerator

class LottoStore(
    purchaseInfo: PurchaseInfo,
    lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator(),
) {
    val lottos = List(purchaseInfo.amount) { Lotto(lottoNumberGenerator.generate()) }
}
