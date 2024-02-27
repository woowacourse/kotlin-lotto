package model.lottery

import WinningLottery
import model.Quantity
import model.WinningRank
import model.WinningResult

class LotteryResultEvaluator(private val lotteryRankEvaluator: LotteryRankEvaluator = LotteryRankEvaluator()) {
    fun evaluate(
        lotteries: Lotteries,
        winningLottery: WinningLottery,
    ): WinningResult {
        val winningResult = DEFAULT_WINNING_RESULT

        lotteries.lotteries.forEach {
            val rank = lotteryRankEvaluator.evaluate(it, winningLottery)
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
