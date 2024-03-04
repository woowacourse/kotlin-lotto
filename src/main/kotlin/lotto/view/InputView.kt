package lotto.view

import lotto.model.puchaseinformation.PurchaseInformation

object InputView {
    private const val HEADER_READ_AMOUNT = "구입금액을 입력해 주세요."
    private const val HEADER_READ_MANUAL_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
    private const val HEADER_READ_MANUAL_LOTTERIES = "\n수동으로 구매할 번호를 입력해 주세요."
    private const val HEADER_READ_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val HEADER_READ_BONUS = "보너스 볼을 입력해 주세요."
    private const val LOTTERY_DELIMITER = ","

    fun readAmount(): Int? {
        println(HEADER_READ_AMOUNT)
        return readln().toIntOrNull()
    }

    fun readManualCount(): Int? {
        println(HEADER_READ_MANUAL_COUNT)
        return readln().toIntOrNull()
    }

    fun readManualLotteries(purchaseInformation: PurchaseInformation): List<List<String>> {
        println(HEADER_READ_MANUAL_LOTTERIES)
        val manualLotteriesInput =
            List(purchaseInformation.manualLotteryCount) {
                readln()
            }

        return splitManualLotteries(manualLotteriesInput)
    }

    private fun splitManualLotteries(input: List<String>): List<List<String>> {
        val manualLotteries = mutableListOf<List<String>>()

        input.forEach {
            manualLotteries.add(it.trim().split(","))
        }
        return manualLotteries
    }

    fun readWinningLottery(): List<String> {
        println(HEADER_READ_WINNING_NUMBERS)
        return splitWinningLottery(readln())
    }

    private fun splitWinningLottery(input: String): List<String> = input.split(LOTTERY_DELIMITER)

    fun readBonus(): String {
        println(HEADER_READ_BONUS)
        return readln()
    }
}
