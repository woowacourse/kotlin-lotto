package lottogame.view

import lottogame.model.Count
import lottogame.model.LottoNumber
import lottogame.model.Money

interface LottoGameInputView {
    fun inputPurchaseExpense(): Money

    fun inputManualLottoCount(): Count

    fun inputManualLottoNumbers(size: Int): List<List<LottoNumber>>

    fun inputWinningNumbers(): List<LottoNumber>

    fun inputBonusNumber(): Int
}
