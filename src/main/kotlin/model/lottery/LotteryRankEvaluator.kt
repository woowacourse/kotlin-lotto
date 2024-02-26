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

        return when {
            matchCount == WinningRank.FIRST.matchNumbers -> WinningRank.FIRST
            matchCount == WinningRank.SECOND.matchNumbers && bonusMatch -> WinningRank.SECOND
            matchCount == WinningRank.THIRD.matchNumbers && !bonusMatch -> WinningRank.THIRD
            matchCount == WinningRank.FOURTH.matchNumbers -> WinningRank.FOURTH
            matchCount == WinningRank.FIFTH.matchNumbers -> WinningRank.FIFTH
            else -> WinningRank.NONE
        }
    }
}
