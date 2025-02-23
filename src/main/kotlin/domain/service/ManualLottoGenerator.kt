package domain.service

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.PurchasePrice

class ManualLottoGenerator(
    private val money: PurchasePrice,
    private val manualLottoNumbers: Set<Int>,
) : LottoNumbersGenerator {
    fun makeLotto(): List<Lotto> {
        val purchaseLottoCount = money.value / PurchasePrice.Companion.STANDARD_AMOUNT_UNIT
        return List(purchaseLottoCount) { makeOneLotto(makeLottoNumbers()) }
    }

    override fun makeLottoNumbers(): Set<LottoNumber> = manualLottoNumbers.map { LottoNumber(it) }.toSet()

    private fun makeOneLotto(lottoNumbers: Set<LottoNumber>): Lotto = Lotto(lottoNumbers)
}
