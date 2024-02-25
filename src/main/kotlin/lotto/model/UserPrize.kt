package lotto.model

class UserPrize(
    private val matchResult: Map<LottoPrize, Int>
) {
    private var matches: MutableMap<LottoPrize, Int> = mutableMapOf()

    init {
        matches = matchResult.toMutableMap()
        LottoPrize.entries.forEach { lottoPrize ->
            if (!matchResult.contains(lottoPrize)) {
                matches[lottoPrize] = 0
            }
        }
    }

    fun prizeCalculate(): Long {
        return matches.entries.sumOf {
            (it.key.getPrice() * it.value).toLong()
        }
    }

    fun prizeRateCalculate(prize: Long, charge: Double): Double {
        return prize / charge
    }

    fun getUserPrize(): Map<LottoPrize, Int> {
        return matches
    }
}
