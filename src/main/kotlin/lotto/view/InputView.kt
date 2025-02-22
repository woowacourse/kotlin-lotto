package lotto.view

object InputView {
    fun inputPurchasePrice(): String {
        println(PRICE_INPUT_MESSAGE)
        return readlnOrNull() ?: ""
    }

    fun inputWinningNumber(): List<Int> {
        println(WINNING_NUMBER_INPUT_MESSAGE)
        return readlnOrNull()?.split(",")?.map { it.trim().toInt() } ?: emptyList()
    }

    fun inputBonusNumber(): String {
        println(BONUS_BALL_INPUT_MESSAGE)
        return readlnOrNull() ?: ""
    }

    private const val PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요."
    private const val WINNING_NUMBER_INPUT_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."
}
