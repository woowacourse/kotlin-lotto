package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.PurchasedLottos

class PurchasedLottosMaker {
    fun makePurchasedLotto(manualLottos: List<Lotto>, lottoAutoCount: LottoCount): PurchasedLottos {
        val manualPurchasedLottos =
            PurchasedLottos.from(LottoCount(manualLottos.size), SequentialLottoNumberGenerator(manualLottos))
        val autoPurchasedLottos = PurchasedLottos.from(lottoAutoCount, RandomLottoGenerator())
        return manualPurchasedLottos.merge(autoPurchasedLottos)
    }
}
