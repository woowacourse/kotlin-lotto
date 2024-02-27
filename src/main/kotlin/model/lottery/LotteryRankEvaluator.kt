package model.lottery

import WinningLottery
import model.WinningRank

class LotteryRankEvaluator {
    fun evaluate(
        lottery: Lottery,
        winningLottery: WinningLottery,
    ): WinningRank {
        val matchCount = lottery.compareLottery(winningLottery.lottery)
        val bonusMatch = lottery.contains(winningLottery.bonusNumber)
        return WinningRank.of(matchCount, bonusMatch)
    }
}
