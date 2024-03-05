package model.winning

import model.Money
import model.Quantity

enum class WinningRank(
    val numbersMatchQuantity: Quantity,
    val winningPrize: Money,
) {
    FIRST(Quantity(6), Money.wons(2_000_000_000)),
    SECOND(Quantity(5), Money.wons(30_000_000)),
    THIRD(Quantity(5), Money.wons(1_500_000)),
    FOURTH(Quantity(4), Money.wons(50_000)),
    FIFTH(Quantity(3), Money.wons(5_000)),

    // NONE 의 matchNumbers 의 0은 편의상 적은 값으로 실제값과 다를 수 있음. (0,1,2 의 경우)
    NONE(Quantity(0), Money.ZERO),
    ;

    companion object {
        fun of(
            numbersMatchQuantity: Quantity,
            bonusNumberMatch: Boolean,
        ): WinningRank {
            if (numbersMatchQuantity == SECOND.numbersMatchQuantity && bonusNumberMatch) {
                return SECOND
            }
            return values().find { numbersMatchQuantity == it.numbersMatchQuantity && it != SECOND } ?: NONE
        }
    }
}
