package lotto.model

enum class LottoPrize(
    val matchNumbers: Int,
    val price: Int,
    val requiresBonus: Boolean = false,
) {
    BOOM(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    ;

    companion object {
        fun findRanking(
            matchCount: Int,
            matchBonus: Boolean,
        ): LottoPrize {
            // SECOND 순위를 특별 처리
            if (matchCount == 5 && matchBonus) {
                return SECOND
            }
            return entries.find { it.matchNumbers == matchCount && (!it.requiresBonus || !matchBonus) } ?: BOOM
        }
    }
}
