package lotto.domain

import java.math.BigDecimal
import java.math.RoundingMode

object LotteryResult {
    private const val NUMBER_OF_DECIMAL_PLACES = 2

    fun getRanks(tickets: List<Lottery>, winningLottery: WinningLottery): Map<Rank, Int> {
        val ranks = tickets.map {
            Rank.valueOf(
                it.countMatches(winningLottery.lottery),
                it.containBonusNumber(winningLottery.bonusNumber)
            )
        }
        return Rank.values().associateWith { key -> ranks.count { key == it } }
    }

    fun getTotalPrize(ranks: Map<Rank, Int>): Long {
        val prize = ranks.map { (rank, count) ->
            rank.calculatePrize(count)
        }.sum()
        return prize
    }

    fun getProfit(purchase: PurchaseAmount, prize: Long): Double {
        return BigDecimal(prize).divide(BigDecimal(purchase.amount), NUMBER_OF_DECIMAL_PLACES, RoundingMode.FLOOR).toDouble()
    }
}
