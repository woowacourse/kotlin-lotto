package view

class InputView {
    fun readPurchaseAmount(): String {
        println(GUIDE_INPUT_PURCHASE_AMOUNT)
        return readln()
    }

    fun readWinningNumbers(): String {
        println(GUIDE_INPUT_WINNING_NUMBERS)
        return readln()
    }

    fun readBonusNumber(): String {
        println(GUIDE_INPUT_BONUS_NUMBER)
        return readln()
    }

    companion object {
        private const val GUIDE_INPUT_PURCHASE_AMOUNT = "구입 금액을 입력해주세요."
        private const val GUIDE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val GUIDE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
