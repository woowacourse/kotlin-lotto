package view

class InputView {
    fun readPurchasePrice(): Int {
        println(MESSAGE_INPUT_PURCHASE_PRICE)
        return readln().toIntOrNull() ?: run {
            println("잘못된 구입 금액 형식입니다.")
            readPurchasePrice()
        }
    }

    fun readManualLottoAmount(): Int {
        println(MESSAGE_INPUT_MANUAL_LOTTO_AMOUNT)
        return readln().toIntOrNull() ?: run {
            println("잘못된 구매 수량 형식입니다.")
            readManualLottoAmount()
        }
    }

    fun printReadManualLottoNumbers() {
        println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBER)
    }

    fun readManualLottoNumbers(): String {
        val input = readln()
        if (input.matches(INPUT_REGEX)) {
            return input
        }
        println("잘못된 로또 형식 입력입니다.")
        return readManualLottoNumbers()
    }

    fun readWinningNumbers(): String {
        println(MESSAGE_INPUT_WINNING_NUMBER)
        val input = readln()
        if (input.matches(INPUT_REGEX)) {
            return input
        }
        println("잘못된 로또 형식 입력입니다.")
        return readWinningNumbers()
    }

    fun readBonusNumber(): String {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        return readln()
    }

    companion object {
        private val INPUT_REGEX = ("^\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2},\\d{1,2}").toRegex()

        const val MESSAGE_INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
        const val MESSAGE_INPUT_MANUAL_LOTTO_AMOUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        const val MESSAGE_INPUT_MANUAL_LOTTO_NUMBER = "수동으로 구매할 번호를 입력해 주세요."
        const val MESSAGE_INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
        const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
