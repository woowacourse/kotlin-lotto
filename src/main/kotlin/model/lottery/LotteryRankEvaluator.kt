package model.lottery

import model.WinningRank

class LotteryRankEvaluator {
    fun evaluate(
        lottery: Lottery,
        winningLottery: Lottery,
        bonusNumber: LotteryNumber,
    ): WinningRank {
        val matchCount = LottoNumberComparator().compareNumbers(lottery, winningLottery)
        val bonusMatch = LottoBonusNumberChecker().containsBonusNumber(lottery, bonusNumber)

        return WinningRank.of(matchCount, bonusMatch)
    }
}
