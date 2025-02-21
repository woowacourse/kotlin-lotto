package lotto.domain.model

enum class LottoRank(val matchCount: Int, val winningAmount: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun calculate(
            matchCount: Int,
            matchBonus: Boolean,
        ): LottoRank {
            return when {
                matchCount == FIRST.matchCount -> FIRST
                matchCount == SECOND.matchCount && matchBonus -> SECOND
                matchCount == THIRD.matchCount -> THIRD
                matchCount == FOURTH.matchCount -> FOURTH
                matchCount == FIFTH.matchCount -> FIFTH
                else -> MISS
            }
        }
    }
}
