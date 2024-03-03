package model

enum class Rank(val countOfMatch: Int, val winningMoney: Money, val isBonusMatch: Boolean? = null) {
    FIRST(6, Money(2_000_000_000)),
    SECOND(5, Money(30_000_000), true),
    THIRD(5, Money(1_500_000), false),
    FOURTH(4, Money(50_000)),
    FIFTH(3, Money(5_000)),
    MISS(0, Money(0)),
    ;

    companion object {
        fun decideRank(
            countOfMatch: Int,
            bonusMatch: Boolean,
        ): Rank {
            return entries.find {
                it.countOfMatch == countOfMatch && (it.isBonusMatch == null || it.isBonusMatch == bonusMatch)
            } ?: MISS
        }
    }
}
