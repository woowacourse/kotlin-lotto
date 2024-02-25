package model

enum class LottoPrize(private val countOfMatch: Int, private val winningAmount: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0), ;

    fun getCountOfMatch(): Int = countOfMatch

    fun getWinningAmount(): Int = winningAmount

    companion object {
        fun getLottoPrize(
            countOfMatch: Int,
            bonusMatched: Boolean,
        ): LottoPrize {
            return when (countOfMatch) {
                6 -> FIRST
                5 -> SECOND.takeIf { bonusMatched } ?: THIRD
                4 -> FOURTH
                3 -> FIFTH
                else -> MISS
            }
        }

        fun getLottoPrizeByOrdinal(ordinal: Int): LottoPrize {
            return entries[ordinal]
        }
    }
}
