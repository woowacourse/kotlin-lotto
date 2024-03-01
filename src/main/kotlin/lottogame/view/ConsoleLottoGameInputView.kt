package lottogame.view

import lottogame.model.LottoNumber
import lottogame.model.Money
import lottogame.model.generator.GeneralLottoNumber

class ConsoleLottoGameInputView : LottoGameInputView {
    override fun inputPurchaseExpense(): Money {
        println(MESSAGE_INPUT_PURCHASE_EXPENSE)
        return readln().toIntOrNull()?.let(::Money) ?: inputPurchaseExpense()
    }

    override fun inputManualLottoCount(): Int {
        println(MESSAGE_INPUT_MANUAL_LOTTO_COUNT)
        return readln().toIntOrNull() ?: inputManualLottoCount()
    }

    override fun inputManualLottoNumbers(size: Int): List<List<LottoNumber>> {
        println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS)
        return List(size) {
            readln().split(DELIMITER).map {
                it.trim().toIntOrNull()?.let(::GeneralLottoNumber) ?: return inputManualLottoNumbers(size)
            }
        }
    }

    override fun inputWinningNumbers(): List<LottoNumber> {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
        val winningNumbers =
            readln().split(DELIMITER).map {
                it.trim().toIntOrNull()?.let(::GeneralLottoNumber) ?: return inputWinningNumbers()
            }
        return winningNumbers
    }

    override fun inputBonusNumber(): Int {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        return readln().toIntOrNull() ?: return inputBonusNumber()
    }

    companion object {
        private const val MESSAGE_INPUT_PURCHASE_EXPENSE = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val DELIMITER = ","
    }
}
