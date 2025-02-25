package domain.service

import domain.model.Lotto
import domain.model.PurchasePrice
import domain.strategy.KoreanLottoGenerator
import domain.strategy.LottoCountry

class LottoGenerator(
    private val money: PurchasePrice,
) {
    fun getAutoLottoAmount(purchaseManualLottoCount: Int): Int {
        val purchaseLottoCount = money.value / PurchasePrice.STANDARD_AMOUNT_UNIT
        return purchaseLottoCount - purchaseManualLottoCount
    }

    fun makeLottos(
        purchaseManualLottoCount: Int,
        manualLottoNumber: List<Lotto>,
    ): List<Lotto> {
        return manualLottoNumber + List(getAutoLottoAmount(purchaseManualLottoCount)) { makeLotto(KoreanLottoGenerator()) }
    }

    private fun makeLotto(lottoCountry: LottoCountry): Lotto {
        val newLotto = lottoCountry.generateNumber()
        return Lotto(newLotto)
    }
}
