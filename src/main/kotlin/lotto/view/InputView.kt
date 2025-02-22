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

    fun inputBonusNumber(): Int {
        println(BONUS_BALL_INPUT_MESSAGE)
        val input = readlnOrNull() ?: ""

        validateBonusNumber(input)
        return input.toInt()
    }

    fun validateBonusNumber(bonusNum: String) {
        require(bonusNum.isNotBlank()) { BONUS_BALL_NULL_ERROR_MESSAGE }
        require(bonusNum.all { it.isDigit() }) { BONUS_BALL_NUMBER_ERROR_MESSAGE }
    }

    private const val PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요."
    private const val WINNING_NUMBER_INPUT_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."
    private const val BONUS_BALL_NUMBER_ERROR_MESSAGE = "보너스 볼 번호는 숫자로 입력해주세요"
    private const val BONUS_BALL_NULL_ERROR_MESSAGE = "보너스 볼 번호는 공백이 불가합니다"
}
