package domain.service

import domain.model.Lotto
import domain.model.PurchaseLotto
import domain.model.PurchasePrice
import domain.strategy.KoreanLottoGenerator
import domain.strategy.LottoCountry

class LottoGenerator(
    private val money: PurchasePrice,
) {
    fun makeLotto(): PurchaseLotto {
        val purchaseLottoCount = money.value / PurchasePrice.STANDARD_AMOUNT_UNIT
        return PurchaseLotto(List(purchaseLottoCount) { makeOneLotto(KoreanLottoGenerator()) })
    }

    private fun makeOneLotto(lottoCountry: LottoCountry): Lotto {
        val newLotto = lottoCountry.generateNumber()
        return Lotto(newLotto)
    }
}
