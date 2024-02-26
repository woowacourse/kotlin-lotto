package model.lottery

class LottoNumberComparator {
    fun compareNumbers(
        lottery: Lottery,
        winningLottery: Lottery,
    ): Int = lottery.lotteryNumbers.count { it in winningLottery.lotteryNumbers }
}
