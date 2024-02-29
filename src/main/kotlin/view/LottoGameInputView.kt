package view

interface LottoGameInputView {
    fun inputPurchaseExpense(): Int

    fun inputManualPurchaseCount(): Int

    fun inputManualLotteryNumber(count: Int): List<List<Int>>

    fun inputLotteryNumbers(message: String): List<Int>

    fun inputWinningNumbers(): List<Int>

    fun inputBonusNumber(): Int
}
