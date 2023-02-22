package lotto.model

import lotto.entity.Money

enum class Rank(val countOfMatch: Int, val winningMoney: Money) {
    FIRST(6, Money(2_000_000_000)),
    SECOND(5, Money(30_000_000)),
    THIRD(5, Money(1_500_000)),
    FOURTH(4, Money(50_000)),
    FIFTH(3, Money(5_000)),
    MISS(0, Money(0));

    fun determine(countOfMatch: Int, isMatchBonus: Boolean): Boolean {
        if (this == MISS && countOfMatch in 0..2) return true
        if (this == SECOND && !isMatchBonus) return false
        return this.countOfMatch == countOfMatch
    }
}
