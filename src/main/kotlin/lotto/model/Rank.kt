package lotto.model

enum class Rank(
    val countOfMatch: Int,
    val winningMoney: Int,
) {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    ;

    companion object {
        fun from(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank =
            when {
                countOfMatch < FIFTH.countOfMatch -> MISS
                countOfMatch == SECOND.countOfMatch && matchBonus -> SECOND
                else ->
                    entries.find { rank ->
                        rank.countOfMatch == countOfMatch
                    } ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 랭크입니다")
            }
    }
}
