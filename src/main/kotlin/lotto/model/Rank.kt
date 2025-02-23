package lotto.model

enum class Rank(
    val countOfMatch: Int,
    val winningMoney: Int,
    val matchBonus: Boolean = false,
) {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000),
    ;

    companion object {
        fun from(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            if (isMiss(countOfMatch)) return MISS

            return entries.find { rank ->
                rank.countOfMatch == countOfMatch && rank.matchBonus == matchBonus
            } ?: MISS
        }

        private fun isMiss(countOfMatch: Int): Boolean = countOfMatch <= 2
    }
}
