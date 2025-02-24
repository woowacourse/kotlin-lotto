package lotto.view

class InputView {
    fun inputPurchase(): String {
        println(INPUT_PURCHASE_MESSAGE)
        return readln()
    }

    fun inputWinningNumbers(): List<Int> {
        println("\n" + INPUT_WINNING_NUMBERS_MESSAGE)
        val input = readln()

        return input.split(",")
            .map { it.trim().toInt() }
            .toList()
    }

    fun inputBonusNumber(): Int {
        println(INPUT_BONUS_NUMBER_MESSAGE)
        return readln().toInt()
    }

    companion object {
        private const val INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
    }
}
