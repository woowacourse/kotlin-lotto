package lotto.view

object InputView {
    private const val GET_PURCHASE_MONEY_SCRIPT = "구입금액을 입력해 주세요."
    private const val GET_MAIN_LOTTO_NUMBERS_SCRIPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val GET_BONUS_LOTTO_NUMBER_SCRIPT = "보너스 볼을 입력해 주세요."
    private const val MAIN_LOTTO_NUMBERS_DELIMITER = ','
    private const val ERROR_NOT_NUMBER = "숫자를 입력해야 합니다."
    private const val ERROR_NOT_COMMA_NUMBER = "숫자와 콤마만 입력해야 합니다."

    fun getPurchaseMoney(): Int {
        println(GET_PURCHASE_MONEY_SCRIPT)
        return readln().trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_NUMBER)
    }

    fun getMainLottoNumber(): List<Int> {
        println(GET_MAIN_LOTTO_NUMBERS_SCRIPT)
        return readln().split(MAIN_LOTTO_NUMBERS_DELIMITER)
            .map { it.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_COMMA_NUMBER) }
    }

    fun getBonusLottoNumber(): Int {
        println(GET_BONUS_LOTTO_NUMBER_SCRIPT)
        return readln().trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_NUMBER)
    }
}
