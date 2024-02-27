import model.lottery.Lottery
import model.lottery.LotteryNumber
import model.winning.WinningRank

data class WinningLottery(val lottery: Lottery, val bonusNumber: LotteryNumber) {
    init {
        require(!lottery.lotteryNumbers.contains(bonusNumber))
    }

    fun evaluate(lottery: Lottery): WinningRank {
        val matchCount = lottery.compareLottery(this.lottery)
        val bonusMatch = lottery.contains(bonusNumber)
        return WinningRank.of(matchCount, bonusMatch)
    }
}
