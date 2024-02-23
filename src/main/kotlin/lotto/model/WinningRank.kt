package lotto.model

enum class WinningRank(val countOfMatch: Int, val winningMoney: Long) {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000), ;

    companion object {
        fun convert(
            matchCount: Int,
            matchBonus: Boolean,
        ): WinningRank =
            when (matchCount) {
                FIRST.countOfMatch -> FIRST
                SECOND.countOfMatch -> if (matchBonus) SECOND else THIRD
                FOURTH.countOfMatch -> FOURTH
                FIFTH.countOfMatch -> FIFTH
                else -> MISS
            }
    }
}
