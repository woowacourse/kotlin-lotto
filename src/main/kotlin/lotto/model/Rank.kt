package lotto.model

enum class Rank(val countOfMatch: Int, val winningAmount: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun getRank(
            countOfMatch: Int,
            bonusMatched: Boolean,
        ): Rank {
            return enumValues<Rank>()
                .firstOrNull { it.countOfMatch == countOfMatch && (it != SECOND || bonusMatched) }
                ?: MISS
        }
    }
}
