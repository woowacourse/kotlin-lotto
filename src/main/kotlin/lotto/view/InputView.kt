package lotto.view

import lotto.domain.Lottery
import lotto.domain.LotteryNumber
import lotto.domain.PurchaseAmount

class InputView {

    fun readBonusNumber(): LotteryNumber {
        println(REQUIRE_MESSAGE_BONUS_NUMBER)
        val input = readln()
        return LotteryNumber(input.toInt())
    }

    fun readLottery(): Lottery {
        println()
        println(REQUIRE_MESSAGE_WINNING_LOTTERY)
        val input = readln()
        val lotteryNumbers = input.split(", ").map { LotteryNumber(it.toInt()) }
        return Lottery(lotteryNumbers)
    }

    fun readPurchaseAmount(): PurchaseAmount {
        println(REQUIRE_MESSAGE_PURCHASE_AMOUNT)
        val input = readln()
        val purchaseAmount = PurchaseAmount(input.toInt())
        println(NOTICE_FORMAT_MESSAGE_PURCHASE_QUANTITY.format(purchaseAmount.getPurchaseQuantity()))
        return purchaseAmount
    }

    companion object {
        private const val NOTICE_FORMAT_MESSAGE_PURCHASE_QUANTITY = "%d개를 구매했습니다."

        private const val REQUIRE_MESSAGE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val REQUIRE_MESSAGE_WINNING_LOTTERY = "지난 주 당첨 번호를 입력해 주세요."
        private const val REQUIRE_MESSAGE_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
