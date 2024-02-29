package model

enum class Rank(val countOfMatch: Int, val isBonusNumberNecessary: Boolean, val winningMoney: Money) {
    FIRST(6, false, Money(2_000_000_000)),
    SECOND(5, true, Money(30_000_000)),
    THIRD(5, false, Money(1_500_000)),
    FOURTH(4, false, Money(50_000)),
    FIFTH(3, false, Money(5_000)),
    MISS(0, false, Money(0)),
    ;

    companion object {
        fun decideRank(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            return when {
                countOfMatch == 6 -> FIRST
                countOfMatch == 5 && matchBonus -> SECOND
                countOfMatch == 5 && !matchBonus -> THIRD
                countOfMatch == 4 -> FOURTH
                countOfMatch == 3 -> FIFTH
                else -> MISS
            }
        }
    }
}
