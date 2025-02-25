package domain.service

import domain.model.Lotto
import domain.model.PurchasePrice
import domain.strategy.KoreanLottoGenerator
import domain.strategy.LottoCountry

class LottoGenerator(
    private val money: PurchasePrice,
) {
    fun getAutoLottoAmount(manualLottoAmount: Int): Int {
        val lottoAmount = money.value / PurchasePrice.STANDARD_AMOUNT_UNIT
        return lottoAmount - manualLottoAmount
    }

    fun makeLottos(
        manualLottoAmount: Int,
        manualLottoNumber: List<Lotto>,
    ): List<Lotto> {
        return manualLottoNumber + List(getAutoLottoAmount(manualLottoAmount)) { makeLotto(KoreanLottoGenerator()) }
    }

    private fun makeLotto(lottoCountry: LottoCountry): Lotto {
        val newLotto = lottoCountry.generateNumber()
        return Lotto(newLotto)
    }
}
