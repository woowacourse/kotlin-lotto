package view

import model.Count
import model.Lotto
import model.Money

interface LottoGameInputView {
    fun inputPurchaseExpense(): Money

    fun inputManualPurchaseCount(): Count

    fun inputManualLotteries(count: Int): List<Lotto>

    fun inputLotteryNumbers(message: String): Lotto

    fun inputWinningNumbers(): Lotto

    fun inputBonusNumber(): Int
}
