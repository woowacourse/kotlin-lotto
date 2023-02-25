package lotto.model

import lotto.entity.LottoCount
import lotto.entity.PurchasedLottos

class LottoMachine(private val manualLottoGenerator: LottoGenerator, private val autoLottoGenerator: LottoGenerator) {
    fun producePurchasedLottos(lottoManualCount: LottoCount, lottoAutoCount: LottoCount): PurchasedLottos {
        val manualPurchasedLottos = makeLottosByGenerator(lottoManualCount, manualLottoGenerator)
        val autoPurchasedLottos = makeLottosByGenerator(lottoAutoCount, autoLottoGenerator)
        return manualPurchasedLottos + autoPurchasedLottos
    }

    private fun makeLottosByGenerator(lottoCount: LottoCount, lottoGenerator: LottoGenerator): PurchasedLottos {
        return PurchasedLottos(
            (0 until lottoCount.value).map {
                lottoGenerator.generate()
            }
        )
    }
}
