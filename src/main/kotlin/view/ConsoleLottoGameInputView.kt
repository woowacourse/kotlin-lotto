package view

class ConsoleLottoGameInputView : LottoGameInputView {
    override fun inputPurchaseExpense(): Int {
        println(MESSAGE_INPUT_PURCHASE_EXPENSE)
        return readln().toIntOrNull()
            ?: throw IllegalArgumentException(EXCEPTION_NULL.format(EXCEPTION_PURCHASE_EXPENSE))
    }

    override fun inputManualPurchaseCount(): Int {
        println(MESSAGE_INPUT_MANUAL_COUNT)
        return readln().toIntOrNull()
            ?: throw IllegalArgumentException(EXCEPTION_NULL.format(EXCEPTION_MANUAL_LOTTO_COUNT))
    }

    override fun inputManualLotteryNumber(): List<Int> {
        println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS)
        return inputLotteryNumbers(EXCEPTION_MANUAL_LOTTO_NUMBERS)
    }

    override fun inputWinningNumbers(): List<Int> {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
        return inputLotteryNumbers(EXCEPTION_WINNING_NUMBER)
    }

    override fun inputLotteryNumbers(message: String): List<Int> {
        return readln().split(DELIMITER).map {
            it.trim().toIntOrNull()
                ?: throw IllegalArgumentException(EXCEPTION_NULL.format(message))
        }
    }

    override fun inputBonusNumber(): Int {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        return readln().toIntOrNull()
            ?: throw IllegalArgumentException(EXCEPTION_NULL.format(EXCEPTION_BONUS_NUMBER))
    }

    companion object {
        private const val MESSAGE_INPUT_PURCHASE_EXPENSE = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val DELIMITER = ","
        private const val EXCEPTION_NULL = "%s을(를) 입력하지 않았습니다."
        private const val EXCEPTION_PURCHASE_EXPENSE = "로또 구매 금액"
        private const val EXCEPTION_WINNING_NUMBER = "당첨 번호"
        private const val EXCEPTION_BONUS_NUMBER = "보너스 번호"
        private const val EXCEPTION_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또의 수"
        private const val EXCEPTION_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 로또"
    }
}
