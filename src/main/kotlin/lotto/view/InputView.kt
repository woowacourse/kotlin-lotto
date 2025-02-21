package lotto.view

class InputView {
    fun readPurchaseAmount(): String {
        println(INPUT_PURCHASE_AMOUNT)
        return readln().trim()
    }

    fun readWinningNumbers(): List<String> {
        println(INPUT_WINNING_LOTTO)
        return readln().split(COMMA).map { it.trim() }
    }

    fun readBonusNumber(): String {
        println(INPUT_BONUS_NUMBER)
        return readln().trim()
    }

    companion object {
        private const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_LOTTO = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val COMMA = ","
    }
}
