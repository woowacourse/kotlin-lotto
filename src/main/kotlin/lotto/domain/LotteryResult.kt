package lotto.domain

import java.math.BigDecimal
import java.math.RoundingMode

class LotteryResult(val ranks: Map<Rank, Int>) {
    fun getProfit(purchase: PurchaseAmount): Double {
        val prize = getTotalPrize()
        return BigDecimal(prize).divide(BigDecimal(purchase.amount), NUMBER_OF_DECIMAL_PLACES, RoundingMode.FLOOR)
            .toDouble()
    }

    private fun getTotalPrize(): Long {
        val prize = ranks.map { (rank, count) ->
            rank.calculatePrize(count)
        }.sum()
        return prize
    }

    companion object {
        private const val NUMBER_OF_DECIMAL_PLACES = 2
    }
}
