package lotto.view

interface LottoGameInputView {
    fun inputPurchaseExpense(): Int?

    fun inputManualPurchaseCount(): Int?

    fun inputManualLotteryNumber(count: Int): List<List<Int>>?

    fun inputLotteryNumbers(): List<Int>?

    fun inputWinningNumbers(): List<Int>?

    fun inputBonusNumber(): Int?
}
