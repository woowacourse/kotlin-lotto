package model

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
            // winningResult[rank] = winningResult[rank]!! + PLUS_WINNING_COUNT
            winningResult[rank] = winningResult.getOrDefault(key = rank, defaultValue = 0) + PLUS_WINNING_COUNT
        }

        return WinningResult(winningResult.toMap())
    }

    companion object {
        private val DEFAULT_WINNING_RESULT: MutableMap<WinningRank, Int>
            get() =
                mutableMapOf(
                    WinningRank.FIRST to DEFAULT_WINNING_COUNT,
                    WinningRank.SECOND to DEFAULT_WINNING_COUNT,
                    WinningRank.THIRD to DEFAULT_WINNING_COUNT,
                    WinningRank.FOURTH to DEFAULT_WINNING_COUNT,
                    WinningRank.FIFTH to DEFAULT_WINNING_COUNT,
                    WinningRank.NONE to DEFAULT_WINNING_COUNT,
                )

        private const val DEFAULT_WINNING_COUNT = 0
        private const val PLUS_WINNING_COUNT = 1
    }
}
