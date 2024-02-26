package view

interface LottoGameInputView {
    fun inputPurchaseExpense(): Int

    fun inputWinningNumbers(): List<Int>

    fun inputBonusNumber(): Int
}
