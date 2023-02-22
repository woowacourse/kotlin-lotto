package lotto.model

import lotto.entity.LottoCount
import lotto.entity.PurchasedLottos

class LottoMachine(private val manualLottoGenerator: LottoGenerator, private val autoLottoGenerator: LottoGenerator) {
    fun makePurchasedLottos(lottoManualCount: LottoCount, lottoAutoCount: LottoCount): PurchasedLottos {
        val manualPurchasedLottos = PurchasedLottos.from(lottoManualCount, manualLottoGenerator)
        val autoPurchasedLottos = PurchasedLottos.from(lottoAutoCount, autoLottoGenerator)
        return manualPurchasedLottos + autoPurchasedLottos
    }
}
