package lotto.view

import lotto.domain.Lotteries
import lotto.domain.Lottery
import lotto.domain.LotteryNumber
import lotto.domain.PurchaseAmount

class InputView {

    fun readBonusNumber(): LotteryNumber {
        println(REQUIRE_MESSAGE_BONUS_NUMBER)
        val input = readln()
        return LotteryNumber(input.toInt())
    }

    fun readWinningLottery(): Lottery {
        println()
        println(REQUIRE_MESSAGE_WINNING_LOTTERY)
        val input = readln()
        val lotteryNumbers = input.split(", ").map { LotteryNumber(it.toInt()) }
        return Lottery(lotteryNumbers)
    }

    fun readLotteries(count: Int): Lotteries {
        println()
        println(REQUIRE_MESSAGE_AUTO_LOTTERIES)

        val lotteries: MutableList<Lottery> = mutableListOf()
        repeat(count) {
            val lottery: Lottery = Lottery(readln().split(", ").map { LotteryNumber(it.toInt()) })
            lotteries.add(lottery)
        }

        return Lotteries(lotteries)
    }

    fun readPurchaseAmount(): PurchaseAmount {
        println(REQUIRE_MESSAGE_PURCHASE_AMOUNT)
        val amount = readln()
        println()
        println(REQUIRE_MESSAGE_AUTO_LOTTERIES_SIZE)
        val autoNumber = readln()

        return PurchaseAmount(amount.toInt(), autoNumber.toInt())
    }

    companion object {
        private const val REQUIRE_MESSAGE_AUTO_LOTTERIES = "수동으로 구매할 번호를 입력해 주세요."
        private const val REQUIRE_MESSAGE_AUTO_LOTTERIES_SIZE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val REQUIRE_MESSAGE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val REQUIRE_MESSAGE_WINNING_LOTTERY = "지난 주 당첨 번호를 입력해 주세요."
        private const val REQUIRE_MESSAGE_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
