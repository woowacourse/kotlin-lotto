package lotto.view

object InputView {
    fun inputPurchasePrice(): Int {
        println(PRICE_INPUT_MESSAGE)
        val price = readlnOrNull() ?: ""
        validatePurchaeePrice(price)

        return price.toInt()
    }

    fun inputWinningNumber(): List<Int> {
        println(WINNING_NUMBER_INPUT_MESSAGE)
        return readlnOrNull()?.split(",")?.map { it.trim().toInt() } ?: emptyList()
    }

    fun inputBonusNumber(): Int {
        println(BONUS_BALL_INPUT_MESSAGE)
        val bonusNum = readlnOrNull() ?: ""

        validateBonusNumber(bonusNum)
        return bonusNum.toInt()
    }

    fun validateBonusNumber(bonusNum: String) {
        require(bonusNum.isNotBlank()) { BONUS_BALL_NULL_ERROR_MESSAGE }
        require(bonusNum.all { it.isDigit() }) { BONUS_BALL_NUMBER_ERROR_MESSAGE }
    }

    fun validatePurchaeePrice(price: String) {
        require(price.all { it.isDigit() }) { PRICE_NUMBER_ERROR_MESSAGE }
    }

    private const val PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요."
    private const val WINNING_NUMBER_INPUT_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."
    private const val BONUS_BALL_NUMBER_ERROR_MESSAGE = "보너스 볼 번호는 숫자로 입력해주세요"
    private const val BONUS_BALL_NULL_ERROR_MESSAGE = "보너스 볼 번호는 공백이 불가합니다"
    private const val PRICE_NUMBER_ERROR_MESSAGE = "구입 금액은 숫자로 입력해주세요"
}
