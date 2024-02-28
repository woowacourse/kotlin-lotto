package lotto.model

enum class Rank(val countOfMatch: Int, val winningMoney: Int, val matchBonus: Boolean? = null) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            return entries.find { lottoRank ->
                lottoRank.countOfMatch == countOfMatch && (lottoRank.matchBonus == null || lottoRank.matchBonus == matchBonus)
            } ?: MISS
        }
    }
}
