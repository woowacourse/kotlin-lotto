package domain.service

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.PurchasePrice
import domain.strategy.AutoLottoGenerator
import domain.strategy.LottoGeneratorType
import domain.strategy.ManualLottoGenerator

class LottoStore(
    private val money: PurchasePrice,
) {
    fun getLottoAmount(): Int {
        return money.value / PurchasePrice.STANDARD_AMOUNT_UNIT
    }

    fun makeLottos(
        manualLottoAmount: Int,
        manualLottoNumber: List<List<LottoNumber>>,
    ): List<Lotto> {
        return lottoType(ManualLottoGenerator(manualLottoNumber)) + lottoType(AutoLottoGenerator(getLottoAmount(), manualLottoAmount))
    }

    private fun lottoType(lottoCountry: LottoGeneratorType): List<Lotto> = lottoCountry.generateNumber()
}
