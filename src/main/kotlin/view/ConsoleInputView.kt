package view

class ConsoleInputView : InputView {
    override fun getPurchasePrice(): Int {
        println(GET_PURCHASE_PRICE_MESSAGE)
        return readln().toInt()
    }

    override fun getWinningTicket(): List<Int> {
        println(GET_WINNING_TICKET_MESSAGE)
        return readln().split(",").map { it.trim().toInt() }
    }

    override fun getBonusNumber(): Int {
        println(GET_BONUS_NUMBER_MESSAGE)
        return readln().toInt()
    }

    companion object {
        private const val GET_PURCHASE_PRICE_MESSAGE = "구입금액을 입력해 주세요."
        private const val GET_WINNING_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val GET_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
    }
}
