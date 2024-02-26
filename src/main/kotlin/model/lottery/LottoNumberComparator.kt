package model.lottery

import model.Quantity

class LottoNumberComparator {
    fun compareNumbers(
        lottery: Lottery,
        winningLottery: Lottery,
    ): Quantity = Quantity(lottery.lotteryNumbers.count { it in winningLottery.lotteryNumbers })
}
