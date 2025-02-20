package domain.service

import domain.model.Lotto
import domain.model.PurchaseLotto
import domain.model.PurchasePrice

class LottoGenerator(
    private val money: PurchasePrice,
) {
    fun makeLotto(): PurchaseLotto {
        val purchaseLottoCount = money.value / PurchasePrice.Companion.STANDARD_AMOUNT_UNIT
        return PurchaseLotto(List<Lotto>(purchaseLottoCount) { makeOneLotto() })
    }

    private fun makeOneLotto(): Lotto {
        val newLotto = (LOTTO_MIN..LOTTO_MAX).shuffled().take(6)
        return Lotto(newLotto)
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
    }
}
