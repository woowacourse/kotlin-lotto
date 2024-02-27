package model.lottery

import WinningLottery
import model.WinningRank

class LotteryRankEvaluator(
    private val lottoNumberComparator: LottoNumberComparator = LottoNumberComparator(),
    private val lottoBonusNumberChecker: LottoBonusNumberChecker = LottoBonusNumberChecker(),
) {
    fun evaluate(
        lottery: Lottery,
        winningLottery: WinningLottery,
    ): WinningRank {
        val matchCount = lottoNumberComparator.compareNumbers(lottery, winningLottery.lottery)
        val bonusMatch = lottoBonusNumberChecker.containsBonusNumber(lottery, winningLottery.bonusNumber)

        return WinningRank.of(matchCount, bonusMatch)
    }
}
