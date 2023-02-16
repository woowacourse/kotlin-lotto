package lotto.view

import lotto.domain.Lottery
import lotto.domain.LotteryNumber
import lotto.domain.PurchaseAmount

class InputView {

    fun readPurchaseAmount(): PurchaseAmount {
        println(REQUIRE_MESSAGE_PURCHASE_AMOUNT)
        val input = readln()
        return PurchaseAmount(input.toInt())
    }

    fun readLottery(): Lottery {
        println(REQUIRE_MESSAGE_WINNING_LOTTERY)
        val input = readln()
        val lotteryNumbers = input.split(", ").map { LotteryNumber(it.toInt()) }
        return Lottery(lotteryNumbers)
    }

    fun readBonusNumber(): LotteryNumber {
        println(REQUIRE_MESSAGE_BONUS_NUMBER)
        val input = readln()
        return LotteryNumber(input.toInt())
    }

    companion object {
        private const val REQUIRE_MESSAGE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val REQUIRE_MESSAGE_WINNING_LOTTERY = "지난 주 당첨 번호를 입력해 주세요."
        private const val REQUIRE_MESSAGE_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
