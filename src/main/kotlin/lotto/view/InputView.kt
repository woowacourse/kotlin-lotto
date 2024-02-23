package lotto.view

object InputView {
    private const val PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
    private const val LAST_WEEK_WINNING_NUMBERS_MESSAGE = "지난 주 당첨번호를 입력해 주세요."
    private const val BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요."

    fun readPrice(): String {
        println(PURCHASE_AMOUNT_MESSAGE)
        return readlnOrNull().orEmpty()
    }

    fun readWinningNumbers(): List<String> {
        println(LAST_WEEK_WINNING_NUMBERS_MESSAGE)
        return readlnOrNull().orEmpty().split(",").map { it.trim() }
    }

    fun readBonusNumber(): String {
        println(BONUS_BALL_MESSAGE)
        return readlnOrNull().orEmpty()
    }
}
