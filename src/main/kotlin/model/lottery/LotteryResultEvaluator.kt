package model.lottery

import WinningLottery
import model.PrizeCount
import model.WinningRank
import model.WinningResult

class LotteryResultEvaluator {
    fun evaluate(
        lotteries: Lotteries,
        winningLottery: Lottery,
        bonusNumber: LotteryNumber,
    ): WinningResult {
        val lotteryRankEvaluator = LotteryRankEvaluator()
        val winningResult = DEFAULT_WINNING_RESULT

        lotteries.lotteries.forEach {
            val rank = lotteryRankEvaluator.evaluate(it, winningLottery, bonusNumber)
            winningResult[rank] = winningResult[rank]!! + PrizeCount(1)
        }

        return WinningResult(winningResult.toMap())
    }

    fun evaluate(
        lotteries: Lotteries,
        winningLottery: WinningLottery,
    ): WinningResult {
        val lotteryRankEvaluator = LotteryRankEvaluator()
        val winningResult = DEFAULT_WINNING_RESULT

        lotteries.lotteries.forEach {
            val rank = lotteryRankEvaluator.evaluate(it, winningLottery.lottery, winningLottery.bonusNumber)
            winningResult[rank] = winningResult[rank]!! + PrizeCount(1)
        }

        return WinningResult(winningResult.toMap())
    }

    companion object {
        private val DEFAULT_WINNING_RESULT =
            mutableMapOf(
                WinningRank.FIRST to PrizeCount(0),
                WinningRank.SECOND to PrizeCount(0),
                WinningRank.THIRD to PrizeCount(0),
                WinningRank.FOURTH to PrizeCount(0),
                WinningRank.FIFTH to PrizeCount(0),
                WinningRank.NONE to PrizeCount(0),
            )
    }
}
