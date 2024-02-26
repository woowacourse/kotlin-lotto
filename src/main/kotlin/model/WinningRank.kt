package model

enum class WinningRank(
    val numbersMatchQuantity: Quantity,
    val bonusNumberMatch: Boolean,
    val winningPrize: Money,
) {
    FIRST(Quantity(6), false, Money.wons(2_000_000_000)),
    SECOND(Quantity(5), true, Money.wons(30_000_000)),
    THIRD(Quantity(5), false, Money.wons(1_500_000)),
    FOURTH(Quantity(4), false, Money.wons(50_000)),
    FIFTH(Quantity(3), false, Money.wons(5_000)),

    // NONE 의 matchNumbers 의 0은 편의상 적은 값으로 실제값과 다를 수 있음. (0,1,2 의 경우)
    NONE(Quantity(0), false, Money.ZERO),
    ;

    companion object {
        fun of(
            numbersMatchQuantity: Quantity,
            bonusNumberMatch: Boolean,
        ) = values().find {
            it.numbersMatchQuantity == numbersMatchQuantity &&
                (it.bonusNumberMatch == bonusNumberMatch || it.bonusNumberMatch.not())
        } ?: NONE
    }
}
