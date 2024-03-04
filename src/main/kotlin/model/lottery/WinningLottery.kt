package model.lottery

import model.winning.WinningRank

data class WinningLottery(val lottery: Lottery, val bonusNumber: LotteryNumber) {
    init {
        require(!lottery.lotteryNumbers.contains(bonusNumber))
    }

    fun evaluateWinningRank(lottery: Lottery): WinningRank {
        val matchCount = lottery.matchNumbersQuantity(this.lottery)
        val bonusMatch = bonusNumber in lottery
        return WinningRank.of(matchCount, bonusMatch)
    }
}
