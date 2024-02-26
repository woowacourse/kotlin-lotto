import model.lottery.Lottery
import model.lottery.LotteryNumber

data class WinningLottery(val lottery: Lottery, val bonusNumber: LotteryNumber) {
    init {
        require(!lottery.lotteryNumbers.contains(bonusNumber))
    }
}
