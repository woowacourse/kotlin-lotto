package domain

import domain.model.LottoDrawingResult
import domain.model.Margin
import domain.model.Money

class LottoMachine(
    private val money: Money
) {
    init {
        require(money >= Money(LOTTO_PRICE)) { "구입금액은 ${money.amount}보다 큰 ${LOTTO_PRICE}원 이상이어야 합니다." }
    }

    fun countTicket(): Int {
        val numberOfTicket = money.amount / LOTTO_PRICE
        return numberOfTicket.toInt()
    }

    fun calculateMargin(prize: Money): Margin {
        return Margin(prize.amount * LOTTO_PRICE / money.amount / LOTTO_PRICE.toDouble())
    }

    fun calculateTotalPrize(result: LottoDrawingResult): Money {
        return Money(result.statistics.entries.sumOf { (rank, count) -> rank.winningMoney * count.toLong() })
    }

    companion object {
        private const val LOTTO_PRICE = 1000L
    }
}
