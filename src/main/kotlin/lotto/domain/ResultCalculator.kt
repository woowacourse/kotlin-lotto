package lotto.domain

import java.math.BigDecimal
import java.math.RoundingMode

object ResultCalculator {
    private const val NUMBER_OF_DECIMAL_PLACES = 2

    fun calculateTotalPrize(ranks: Map<Rank, Int>): Long {
        val prize = ranks.map { (rank, count) ->
            rank.calculatePrize(count)
        }.sum()
        return prize
    }

    fun calculateProfit(purchase: PurchaseAmount, prize: Long): Double {
        return BigDecimal(prize).divide(BigDecimal(purchase.amount), NUMBER_OF_DECIMAL_PLACES, RoundingMode.FLOOR).toDouble()
    }
}
