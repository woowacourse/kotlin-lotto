package model.lottery

import model.Quantity
import model.winning.WinningRank
import model.winning.WinningResult

data class Lotteries(val lotteries: List<Lottery>) {
    operator fun plus(other: Lotteries): Lotteries = Lotteries(this.lotteries + other.lotteries)

    fun evaluateWinning(winningLottery: WinningLottery): WinningResult {
        val rankCounts = lotteries.groupingBy { winningLottery.evaluateWinningRank(it) }.eachCount()
        return WinningResult(
            WinningRank.values().associateWith { rankCounts.getOrDefault(it, 0).toQuantity() },
        )
    }
}

private fun Int.toQuantity(): Quantity = Quantity(this)
