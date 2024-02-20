package model

import java.math.BigDecimal

enum class WinningRank(
    matchNumbers: Int,
    bonusNumberMatch: Boolean,
    winningPrize: Money,
) {
    FIFTH(3, false, Money(BigDecimal(5_000))),
    FOURTH(4, false, Money(BigDecimal(50_000))),
    THIRD(5, false, Money(BigDecimal(1_500_000))),
    SECOND(5, true, Money(BigDecimal(30_000_000))),
    FIRST(6, false, Money(BigDecimal(2_000_000_000))),
}
