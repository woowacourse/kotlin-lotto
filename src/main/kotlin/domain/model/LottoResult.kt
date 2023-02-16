package domain.model

enum class LottoResult(val prizeMoney: Int, val matchCount: Int) {
    MISS(0, 0),
    FIFTH(5_000, 3),
    FORTH(50_000, 4),
    THIRD(1_500_000, 5),
    SECOND(30_000_000, 5),
    FIRST(2_000_000_000, 6);

    companion object {
        private const val FIVE_MATCH_COUNT = 5
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): LottoResult {
            if (countOfMatch == FIVE_MATCH_COUNT && matchBonus) {
                return SECOND
            }

            return values().find { lottoResult ->
                lottoResult.matchCount == countOfMatch
            } ?: MISS
        }
    }
}

