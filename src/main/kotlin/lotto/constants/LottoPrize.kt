package lotto.constants

enum class LottoPrize(val matchingCount: Int, val amount: Int) {
    NOTHING(0, 0),
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1500000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000), ;

    companion object {
        fun getLottoPrize(
            matchingCount: Int,
            isMatchingBonus: Boolean,
        ): LottoPrize {
            if (matchingCount == SECOND.matchingCount) {
                return if (isMatchingBonus) SECOND else THIRD
            }
            return entries.find { it.matchingCount == matchingCount } ?: NOTHING
        }
    }
}
