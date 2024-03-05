package lotto.view

import lotto.model.puchaseinformation.PurchaseInformation

object InputView {
    private const val NEW_LINE = "\n"
    private const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
    private const val HEADER_READ_AMOUNT = "구입금액을 입력해 주세요."
    private const val HEADER_READ_MANUAL_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
    private const val HEADER_READ_MANUAL_LOTTERIES = "\n수동으로 구매할 번호를 입력해 주세요."
    private const val HEADER_READ_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val HEADER_READ_BONUS = "보너스 볼을 입력해 주세요."
    private const val LOTTERY_DELIMITER = ","
    private const val LOTTERY_COUNT = 6

    fun readAmount(): Int {
        println(HEADER_READ_AMOUNT)
        val amount = readln().toIntOrNull()
        if (amount != null) return amount
        println("${EXCEPTION_IS_NOT_NUMBER}${NEW_LINE}")
        return readAmount()
    }

    fun readManualCount(): Int {
        println(HEADER_READ_MANUAL_COUNT)
        val manualCount = readln().toIntOrNull()
        if (manualCount != null) return manualCount
        println(EXCEPTION_IS_NOT_NUMBER)
        return readManualCount()
    }

    private fun readManualLottery(): List<Int> {
        val lottery = splitManualLottery(readln()).mapNotNull { it.toIntOrNull() }
        if (lottery.size == LOTTERY_COUNT) return lottery
        println(EXCEPTION_IS_NOT_NUMBER)
        return readManualLottery()
    }

    private fun splitManualLottery(input: String) = input.trim().split(",")

    fun readManualLotteries(purchaseInformation: PurchaseInformation): List<List<Int>> {
        println(HEADER_READ_MANUAL_LOTTERIES)
        return List(purchaseInformation.manualLotteryCount) {
            readManualLottery()
        }
    }

    fun readWinningLottery(): List<Int> {
        println(HEADER_READ_WINNING_NUMBERS)
        val winningLottery = splitWinningLottery(readln()).mapNotNull { it.toIntOrNull() }
        if (winningLottery.size == LOTTERY_COUNT) return winningLottery
        println(EXCEPTION_IS_NOT_NUMBER)
        return readWinningLottery()
    }

    private fun splitWinningLottery(input: String): List<String> = input.split(LOTTERY_DELIMITER)

    fun readBonus(): Int {
        println(HEADER_READ_BONUS)
        val bonus = readln().toIntOrNull()
        if (bonus != null) return bonus
        println(EXCEPTION_IS_NOT_NUMBER)
        return readBonus()
    }
}
