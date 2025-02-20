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
        fun fromMatchResult(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            if (isMiss(countOfMatch)) return MISS

            return entries.find { rank ->
                if (isSecond(countOfMatch, matchBonus)) return SECOND
                rank.countOfMatch == countOfMatch
            } ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 당첨 결과입니다.")
        }

        private fun isSecond(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Boolean = countOfMatch == SECOND.countOfMatch && matchBonus

        private fun isMiss(countOfMatch: Int): Boolean = countOfMatch <= 2
    }
}
