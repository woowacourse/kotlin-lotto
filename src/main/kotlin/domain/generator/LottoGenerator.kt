package domain.generator

import domain.model.Lotto
import domain.model.PurchaseLotto
import domain.model.PurchasePrice
import java.util.TreeSet

class LottoGenerator(money: PurchasePrice) {
    private val purchaseLottoCount = money.value / PurchasePrice.Companion.STANDARD_AMOUNT_UNIT

    fun makeLotto(): PurchaseLotto {
        return PurchaseLotto(List<Lotto>(purchaseLottoCount) { makeOneLotto() })
    }

    private fun makeOneLotto(): Lotto {
        val newLotto = TreeSet<Int>((LOTTO_MIN..LOTTO_MAX).shuffled().take(6))
        return Lotto(newLotto)
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
    }
}
