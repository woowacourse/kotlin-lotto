package lotto.view

class InputView {
    fun getManualLottoCount(): Int {
        while (true) {
            println(MESSAGE_INPUT_MANUAL_LOTTO_COUNT)
            val input = intOrNull(readln())
            if (input != null) return input
        }
    }

    fun getManualLottoNumbers(count: Int): List<List<Int>> {
        while (true) {
            println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBER)
            val input = List(count) { intListOrNull(readln().split(DELIMITERS).map { it.trim() }) }
            if (input.all { it != null }) return input.filterNotNull()
        }
    }

    fun getPurchaseAmount(): Int {
        while (true) {
            println(MESSAGE_INPUT_PURCHASE_AMOUNT)
            val input = intOrNull(readln())
            if (input != null) return input
        }
    }

    fun getWinningNumber(): List<Int> {
        while (true) {
            println(MESSAGE_INPUT_WINNING_NUMBER)
            val input = intListOrNull(readln().split(DELIMITERS).map { it.trim() })
            if (input != null) return input
        }
    }

    fun getBonusNumber(): Int {
        while (true) {
            println(MESSAGE_INPUT_BONUS_NUMBER)
            val input = intOrNull(readln())
            if (input != null) return input
        }
    }

    private fun intOrNull(input: String): Int? {
        return input.toIntOrNull()
    }

    private fun intListOrNull(input: List<String>): List<Int>? {
        input.forEach {
            if (intOrNull(it) == null) return null
        }
        return input.map { it.toInt() }
    }

    companion object {
        private const val MESSAGE_INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_NUMBER = "수동으로 구매할 번호를 입력해 주세요."

        private const val DELIMITERS = ","
    }
}
