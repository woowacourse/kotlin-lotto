package lotto.view

import lotto.model.LottoManualPurchase

object InputView {
    private const val PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
    private const val LAST_WEEK_WINNING_NUMBERS_MESSAGE = "지난 주 당첨번호를 입력해 주세요."
    private const val BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요."
    private const val LOTTO_MANUAL_PURCHASE_COUNT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요."
    private const val LOTTO_MANUAL_PURCHASE_NUMBER_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요."

    fun readMoney(): String {
        println(PURCHASE_AMOUNT_MESSAGE)
        return readlnOrNull().orEmpty()
    }

    fun readLottoManualPurchaseCount(): String {
        println(LOTTO_MANUAL_PURCHASE_COUNT_MESSAGE)
        return readlnOrNull().orEmpty()
    }

    fun readLottoManualPurchaseNumbers(lottoPurchaseCount: LottoManualPurchase): List<List<String>> {
        println(LOTTO_MANUAL_PURCHASE_NUMBER_MESSAGE)
        return List(lottoPurchaseCount.count) { readlnOrNull().orEmpty().split(",").map { it.trim() } }
    }

    fun readWinningNumbers(): List<String> {
        println(LAST_WEEK_WINNING_NUMBERS_MESSAGE)
        return readlnOrNull().orEmpty().split(",").map { it.trim() }
    }

    fun readBonusNumber(): String {
        println(BONUS_BALL_MESSAGE)
        return readlnOrNull().orEmpty()
    }
}
