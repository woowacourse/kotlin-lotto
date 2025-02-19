package lotto.domain

import lotto.Lotto
import lotto.domain.model.PurchaseLotto
import lotto.domain.model.PurchasePrice
import lotto.domain.model.PurchasePrice.Companion.STANDARD_AMOUNT_UNIT
import java.util.TreeSet
import kotlin.collections.shuffled

class LottoGenerator(money: PurchasePrice) {
    val purchaseLottoCount = money.value / STANDARD_AMOUNT_UNIT

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
