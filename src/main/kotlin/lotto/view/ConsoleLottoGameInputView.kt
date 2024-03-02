package lotto.view

class ConsoleLottoGameInputView : LottoGameInputView {
    override fun inputPurchaseExpense(): Int? {
        println(MESSAGE_INPUT_PURCHASE_EXPENSE)
        return readln().toIntOrNull()
    }

    override fun inputManualPurchaseCount(): Int? {
        println(MESSAGE_INPUT_MANUAL_COUNT)
        return readln().toIntOrNull()
    }

    override fun displayManualNumberInputMessage() {
        println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS)
    }

    override fun inputWinningNumbers(): List<Int>? {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
        return inputLotteryNumbers()
    }

    override fun inputLotteryNumbers(): List<Int>? {
        return readln().split(DELIMITER).map {
            it.trim().toIntOrNull()
                ?: return null
        }
    }

    override fun inputBonusNumber(): Int? {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        return readln().toIntOrNull()
    }

    companion object {
        private const val MESSAGE_INPUT_PURCHASE_EXPENSE = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val DELIMITER = ","
    }
}
