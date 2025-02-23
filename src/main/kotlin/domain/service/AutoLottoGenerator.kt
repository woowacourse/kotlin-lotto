package domain.service

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.PurchasePrice

class AutoLottoGenerator(
    private val money: PurchasePrice,
) : LottoNumbersGenerator {
    fun makeLotto(): List<Lotto> {
        val purchaseLottoCount = money.value / PurchasePrice.Companion.STANDARD_AMOUNT_UNIT
        return List(purchaseLottoCount) { makeOneLotto(makeLottoNumbers()) }
    }

    override fun makeLottoNumbers(): Set<LottoNumber> =
        (LOTTO_MIN..LOTTO_MAX)
            .shuffled()
            .take(6)
            .map { LottoNumber(it) }
            .toSet()

    private fun makeOneLotto(lottoNumbers: Set<LottoNumber>): Lotto = Lotto(lottoNumbers)

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
    }
}
