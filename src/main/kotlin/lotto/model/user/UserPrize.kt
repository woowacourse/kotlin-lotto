package lotto.model.user

import lotto.model.LottoPrize

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

    fun prizeRateCalculate(prize: Long, charge: Double): Double? {
        return runCatching {
            prize / charge
        }.getOrElse {
            null
        }
    }

    fun getUserPrize(): Map<LottoPrize, Int> {
        return matches
    }
}
