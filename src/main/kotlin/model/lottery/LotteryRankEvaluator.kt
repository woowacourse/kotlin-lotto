package model.lottery

import WinningLottery
import model.WinningRank

class LotteryRankEvaluator {
    fun evaluate(
        lottery: Lottery,
        winningLottery: WinningLottery,
    ): WinningRank {
        val matchCount = LottoNumberComparator().compareNumbers(lottery, winningLottery.lottery)
        val bonusMatch = LottoBonusNumberChecker().containsBonusNumber(lottery, winningLottery.bonusNumber)

        return WinningRank.of(matchCount, bonusMatch)
    }
}
