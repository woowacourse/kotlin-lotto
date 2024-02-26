package view

import model.LotteryPurchasePattern

object InputView {
    private const val HEADER_READ_AMOUNT = "구입금액을 입력해 주세요."
    private const val HEADER_READ_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val HEADER_READ_BONUS = "보너스 볼을 입력해 주세요."
    private const val HEADER_READ_PURCHASE_PATTERN = "\n수동으로 구매할 로또 수를 입력해 주세요."
    private const val HEADER_READ_MANUAL_LOTTERY_CANDIDATES = "\n수동으로 구매할 번호를 입력해 주세요."

    fun readAmount(): String {
        println(HEADER_READ_AMOUNT)
        return readln()
    }

    fun readPurchasePattern(): String {
        println(HEADER_READ_PURCHASE_PATTERN)
        return readln()
    }

    fun readManualLotteryCandidates(lotteryPurchasePattern: LotteryPurchasePattern): List<String> {
        println(HEADER_READ_MANUAL_LOTTERY_CANDIDATES)

        val lotteryCandidates = mutableListOf<String>()

        repeat(lotteryPurchasePattern.manualLottoCount) {
            lotteryCandidates.add(readln())
        }
        return lotteryCandidates
    }

    fun readWinningLotto(): String {
        println(HEADER_READ_WINNING_NUMBERS)
        return readln()
    }

    fun readBonus(): String {
        println(HEADER_READ_BONUS)
        return readln()
    }
}
