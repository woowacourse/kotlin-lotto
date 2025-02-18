package lotto.domain.model

enum class LottoRank(val matchCount: Int, val winningAmount: Int, val matchBonus: Boolean = false) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun calculate(
            matchCount: Int,
            matchBonus: Boolean = true,
        ): LottoRank {
            return entries.find { it.matchCount == matchCount && it.matchBonus == matchBonus } ?: return MISS
        }
    }
}
