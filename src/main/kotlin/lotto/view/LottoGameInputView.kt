package lotto.view

interface LottoGameInputView {
    fun inputPurchaseExpense(): Int?

    fun inputManualPurchaseCount(): Int?

    fun displayManualNumberInputMessage()

    fun inputLotteryNumbers(): List<Int>?

    fun inputWinningNumbers(): List<Int>?

    fun inputBonusNumber(): Int?
}
