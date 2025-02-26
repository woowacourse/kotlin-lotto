package domain.service

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.PurchasePrice
import domain.strategy.AutoLottoGenerator
import domain.strategy.LottoGeneratorType
import domain.strategy.ManualLottoGenerator

class LottoGenerator(
    private val money: PurchasePrice,
) {
    fun getAutoLottoAmount(manualLottoAmount: Int): Int {
        val lottoAmount = money.value / PurchasePrice.STANDARD_AMOUNT_UNIT
        return lottoAmount - manualLottoAmount
    }

    fun makeLottos(
        manualLottoAmount: Int,
        manualLottoNumber: List<List<LottoNumber>>,
    ): List<Lotto> {
        return lottoType(ManualLottoGenerator(manualLottoNumber)) + lottoType(AutoLottoGenerator(getAutoLottoAmount(manualLottoAmount)))
    }

    private fun lottoType(lottoCountry: LottoGeneratorType): List<Lotto> = lottoCountry.generateNumber()
}
