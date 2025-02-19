package lotto.view

class InputView {
    fun getPurchaseAmount(): String {
        println(MESSAGE_INPUT_PURCHASE_AMOUNT)
        return readln()
    }

    fun getWinningNumber(): String {
        println(MESSAGE_INPUT_WINNING_NUMBER)
        return readln()
    }

    fun getBonusNumber(): String {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        return readln()
    }

    companion object {
        const val MESSAGE_INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        const val MESSAGE_INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
        const val MESSAGE_INPUT_BONUS_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
    }
}
