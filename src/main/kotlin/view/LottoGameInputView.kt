package view

interface LottoGameInputView {
    fun inputPurchaseExpense(): Int

    fun inputManualLottoCount(): Int

    fun inputManualLottoNumbers(size: Int): List<List<Int>>

    fun inputWinningNumbers(): List<Int>

    fun inputBonusNumber(): Int
}
