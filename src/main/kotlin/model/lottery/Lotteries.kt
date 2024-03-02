package model.lottery

import model.Quantity
import model.winning.WinningRank
import model.winning.WinningResult

data class Lotteries(val lotteries: List<Lottery>) {
    operator fun plus(other: Lotteries): Lotteries = Lotteries(this.lotteries + other.lotteries)

    fun evaluateWinning(winningLottery: WinningLottery): WinningResult {
        val winningResult = DEFAULT_WINNING_RESULT

        lotteries.forEach {
            val rank = winningLottery.evaluateWinningRank(it)
            winningResult[rank] = winningResult[rank]!! + Quantity(1)
        }

        return WinningResult(winningResult.toMap())
    }

    companion object {
        private val DEFAULT_WINNING_RESULT =
            mutableMapOf(
                WinningRank.FIRST to Quantity(0),
                WinningRank.SECOND to Quantity(0),
                WinningRank.THIRD to Quantity(0),
                WinningRank.FOURTH to Quantity(0),
                WinningRank.FIFTH to Quantity(0),
                WinningRank.NONE to Quantity(0),
            )
    }
}
