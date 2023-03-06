package model.domain

enum class Rank(
    val countOfMatch: Int,
    val winningMoney: Int,
) {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000) {
        override fun lottoResultReader(countOfMatch: Int, isBonusMatch: Boolean) =
            super.lottoResultReader(countOfMatch, isBonusMatch) and isBonusMatch
    },
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0) {
        override fun lottoResultReader(countOfMatch: Int, isBonusMatch: Boolean) =
            countOfMatch < MINIMUM
    }, ;

    open fun lottoResultReader(countOfMatch: Int, isBonusMatch: Boolean) =
        this.countOfMatch == countOfMatch

    companion object {
        private const val MINIMUM = 3
        fun valueOf(countOfMatch: Int, matchBonus: Boolean) =
            values().find { rank ->
                rank.lottoResultReader(countOfMatch, matchBonus)
            } ?: MISS
    }
}
