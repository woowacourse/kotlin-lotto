package model.lottery

import model.winning.WinningRank

data class WinningLottery(val lottery: Lottery, val bonusNumber: LotteryNumber) {
    init {
        require(!lottery.lotteryNumbers.contains(bonusNumber)) { "당첨 번호와 로또 번호가 중복되면 안됩니다." }
    }

    fun evaluateWinningRank(lottery: Lottery): WinningRank {
        val matchCount = lottery.matchNumbersQuantity(this.lottery)
        val bonusMatch = bonusNumber in lottery
        return WinningRank.of(matchCount, bonusMatch)
    }
}
