package lotto.view

import lotto.domain.Lottery
import lotto.domain.LotteryNumber
import lotto.domain.PurchaseAmount

class InputView {

    fun readPurchaseAmount(): PurchaseAmount {
        val input = readln()
        return PurchaseAmount(input.toInt())
    }

    fun readLottery(): Lottery {
        val input = readln()
        val lotteryNumbers = input.split(", ").map { LotteryNumber(it.toInt()) }
        return Lottery(lotteryNumbers)
    }

    fun readBonusNumber(): LotteryNumber {
        val input = readln()
        return LotteryNumber(input.toInt())
    }
}
