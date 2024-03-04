package lottogame.view

import lottogame.model.GeneralLottoNumber
import lottogame.model.LottoCount
import lottogame.model.LottoNumber
import lottogame.model.Money

class ConsoleLottoGameInputView : LottoGameInputView {
    override tailrec fun inputPurchaseExpense(): Money {
        println(MESSAGE_INPUT_PURCHASE_EXPENSE)
        val amount = readln().toIntOrNull() ?: return inputPurchaseExpense()
        return Money.createOrNull(amount) ?: inputPurchaseExpense()
    }

    override tailrec fun inputManualLottoCount(): LottoCount {
        println(MESSAGE_INPUT_MANUAL_LOTTO_COUNT)
        val count = readln().toIntOrNull() ?: return inputManualLottoCount()
        return runCatching { LottoCount(count) }
            .onFailure { return inputManualLottoCount() }
            .getOrThrow()
    }

    override tailrec fun inputManualLottoNumbers(size: Int): List<List<LottoNumber>> {
        if (size <= 0) return emptyList()
        println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS)
        return List(size) {
            readln().split(DELIMITER).map {
                it.trim().toIntOrNull()?.let(::GeneralLottoNumber) ?: return inputManualLottoNumbers(size)
            }
        }
    }

    override tailrec fun inputWinningNumbers(): List<LottoNumber> {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
        val winningNumbers =
            readln().split(DELIMITER).map {
                it.trim().toIntOrNull()?.let(::GeneralLottoNumber) ?: return inputWinningNumbers()
            }
        return winningNumbers
    }

    override tailrec fun inputBonusNumber(): Int {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        return readln().toIntOrNull() ?: inputBonusNumber()
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
