package lotto.model

import lotto.util.Constant

class UserPrize(
    private val matches: Map<LottoPrize, Int>,
) {

    fun prizeCalculate(): Long {
        return matches.entries.sumOf {
            (it.key.getPrice() * it.value).toLong()
        }
    }

    fun prizeRateCalculate(prize: Long, payCount: Int): Double {
        return prize / payCount / Constant.LOTTO_PRICE
    }

    fun getUserPrize(): Map<LottoPrize, Int> {
        return matches
    }
}
