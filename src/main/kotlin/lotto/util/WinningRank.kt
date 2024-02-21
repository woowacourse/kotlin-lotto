package lotto.util

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

        fun formatByRank(winningRank: WinningRank): String {
            if (winningRank == SECOND) return "${winningRank.countOfMatch}개 일치, 보너스 볼 일치(${winningRank.winningMoney}원)"
            return "${winningRank.countOfMatch}개 일치 (${winningRank.winningMoney}원)"
        }
    }
}
