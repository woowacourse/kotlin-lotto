package view

class InputView {
    fun getPurchasePrice(): Int {
        println(PURCHASE_AMOUNT_PROMPT_MESSAGE)
        return readln().toInt()
    }

    fun getWinningTicket(): List<Int> {
        println(WINNING_NUMBER_PROMPT_MESSAGE)
        return readln().split(",").map { it.trim().toInt() }
    }

    fun getBonusNumber(): Int {
        println(BONUS_BALL_PROMPT_MESSAGE)
        return readln().toInt()
    }

    companion object {
        const val PURCHASE_AMOUNT_PROMPT_MESSAGE = "구입금액을 입력해 주세요."
        const val WINNING_NUMBER_PROMPT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        const val BONUS_BALL_PROMPT_MESSAGE = "보너스 볼을 입력해 주세요."
    }
}
