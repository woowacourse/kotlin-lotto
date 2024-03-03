package lotto.model

class UserPrize(
    private val matches: Map<LottoPrize, Int>,
) {
    fun prizeCalculate(): Long {
        return matches.entries.sumOf {
            (it.key.price * it.value).toLong()
        }
    }

    fun prizeRateCalculate(
        prize: Long,
        charge: Double,
    ): Double {
        return prize / charge
    }

    fun getUserPrize(): Map<LottoPrize, Int> {
        return matches
    }
}
