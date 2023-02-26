package lotto.domain

import java.math.BigDecimal
import java.math.RoundingMode

class LotteryResult(private val tickets: List<Lottery>, private val winningLottery: WinningLottery) {
    val ranks: Map<Rank, Int> by lazy {
        val ranks = tickets.map {
            Rank.valueOf(
                it.countMatches(winningLottery.lottery),
                it.containBonusNumber(winningLottery.bonusNumber)
            )
        }
        Rank.values().associateWith { key -> ranks.count { key == it } }
    }

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
