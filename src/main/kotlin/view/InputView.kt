package view

class InputView {
    fun readPurchasePrice(): String {
        println(MESSAGE_INPUT_PURCHASE_PRICE)
        return readln()
    }

    fun readWinningNumbers(): String {
        println(MESSAGE_INPUT_WINNING_NUMBER)
        return readln()
    }

    fun readBonusNumber(): String {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        return readln()
    }

    companion object {
        const val MESSAGE_INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
        const val MESSAGE_INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
        const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
