package model

class LotteryResultChecker {
    fun judge(lottery: Lottery, winningLottery: Lottery, bonusNumber: LotteryNumber): WinningRank {
        val matchCount = match(lottery, winningLottery)
        val bonusMatch = bonusMatch(lottery, bonusNumber)

        return when {
            matchCount == WinningRank.FIRST.matchNumbers -> WinningRank.FIRST
            matchCount == WinningRank.SECOND.matchNumbers && bonusMatch -> WinningRank.SECOND
            matchCount == WinningRank.THIRD.matchNumbers && !bonusMatch -> WinningRank.THIRD
            matchCount == WinningRank.FOURTH.matchNumbers -> WinningRank.FOURTH
            matchCount == WinningRank.FIFTH.matchNumbers -> WinningRank.FIFTH
            else -> WinningRank.NONE
        }
    }

    // TODO: 클래스 분리를 고려해야 함.
    fun match(lottery: Lottery, winningLottery: Lottery): Int =
        lottery.lotteryNumbers.count { it in winningLottery.lotteryNumbers }

    // TODO: 클래스 분리를 고려해야 함.
    fun bonusMatch(lottery: Lottery, bonusNumber: LotteryNumber): Boolean =
        lottery.lotteryNumbers.any { it == bonusNumber }

}
