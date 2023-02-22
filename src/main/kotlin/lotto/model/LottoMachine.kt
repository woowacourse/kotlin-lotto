package lotto.model

import lotto.entity.LottoCount
import lotto.entity.PurchaseMoney
import lotto.entity.PurchasedLottos

class LottoMachine(private val manualLottoGenerator: LottoGenerator, private val autoLottoGenerator: LottoGenerator) {
    fun makePurchasedLottos(purchaseMoney: PurchaseMoney, lottoManualCount: LottoCount): PurchasedLottos {
        val lottoAutoCount = purchaseMoney.calculateAutoLottoCount(lottoManualCount)
        val manualPurchasedLottos = PurchasedLottos.from(lottoManualCount, manualLottoGenerator)
        val autoPurchasedLottos = PurchasedLottos.from(lottoAutoCount, autoLottoGenerator)
        return manualPurchasedLottos + autoPurchasedLottos
    }
}
