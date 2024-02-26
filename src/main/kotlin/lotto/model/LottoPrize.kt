package lotto.model

enum class LottoPrize(val matchingCount: Int, val amount: Int) {
    NOTHING(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000), ;

    fun isBonus() = this == SECOND

    companion object {
        fun valueOf(
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
