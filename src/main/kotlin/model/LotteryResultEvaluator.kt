package model

class LotteryResultEvaluator {
    fun evaluate(lotteries: Lotteries, winningLottery: Lottery, bonusNumber: LotteryNumber): WinningResult {
        val lotteryRankEvaluator = LotteryRankEvaluator()
        val winningResult = DEFAULT_WINNING_RESULT

        lotteries.lotteries.forEach {
            val rank = lotteryRankEvaluator.evaluate(it, winningLottery, bonusNumber)
            winningResult[rank] = winningResult[rank]!! + 1
        }

        return WinningResult(winningResult.toMap())
    }

    companion object {
        private val DEFAULT_WINNING_RESULT = mutableMapOf(
            WinningRank.FIRST to 0,
            WinningRank.SECOND to 0,
            WinningRank.THIRD to 0,
            WinningRank.FOURTH to 0,
            WinningRank.FIFTH to 0
        )
    }
}
