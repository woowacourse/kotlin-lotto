package lotto.constants

enum class LottoPrize(val matchingCount: Int, val amount: Int) {
    NOTHING(0, 0),
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    companion object {
        fun getLottoPrize(matchingCount: Int, isMatchBonus: Boolean): LottoPrize {
            if (matchingCount == 5) {
                return if (isMatchBonus) SECOND
                else THIRD
            }
            return LottoPrize.values().find { it.matchingCount == matchingCount } ?: NOTHING
        }

    }
}
