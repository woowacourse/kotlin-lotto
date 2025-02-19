package lotto.model

enum class Rank(
    val countOfMatch: Int,
    val winningMoney: Int,
    val matchBonus: Boolean = false,
) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun fromMatchResult(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            return entries.find { rank ->
                val isMatchCountSame = rank.countOfMatch == countOfMatch
                val isMatchBonus = rank.matchBonus == matchBonus

                isMatchCountSame && isMatchBonus
            } ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 당첨 결과입니다.")
        }
    }
}
