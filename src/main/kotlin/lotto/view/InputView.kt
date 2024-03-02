package lotto.view

class InputView {
    fun getPurchasePrice(): Int {
        println(PURCHASE_AMOUNT_PROMPT_MESSAGE)
        return readln().toInt()
    }

    fun getManualLottoCount(): Int {
        println(MANUAL_LOTTO_CNT_PROMPT_MESSAGE)
        return readln().toInt()
    }

    fun getManualLottoTickets(count: Int): List<List<Int>> {
        println(MANUAL_LOTTO_PROMPT_MESSAGE)
        val manualLottoTickets =
            List(count) {
                readln().split(",").map { it.trim() }.map { it.toInt() }
            }
        return manualLottoTickets
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
        const val MANUAL_LOTTO_CNT_PROMPT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        const val MANUAL_LOTTO_PROMPT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
        const val WINNING_NUMBER_PROMPT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        const val BONUS_BALL_PROMPT_MESSAGE = "보너스 볼을 입력해 주세요."
    }
}
