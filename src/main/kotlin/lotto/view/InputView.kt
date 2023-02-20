package lotto.view

object InputView {
    private const val ERROR_WRONG_INPUT = "입력값으로 Null 이 입력되었습니다."
    private const val GET_PURCHASE_MONEY_SCRIPT = "구입금액을 입력해 주세요."
    private const val GET_MAIN_LOTTO_NUMBERS_SCRIPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val GET_BONUS_LOTTO_NUMBER_SCRIPT = "보너스 볼을 입력해 주세요."

    fun getPurchaseMoney(): Int {
        println(GET_PURCHASE_MONEY_SCRIPT)
        return readlnOrNull()?.trim()?.toInt() ?: throw IllegalStateException(ERROR_WRONG_INPUT)
    }

    fun getMainLottoNumbers(): List<Int> {
        println(GET_MAIN_LOTTO_NUMBERS_SCRIPT)
        return readlnOrNull()?.trim()?.split(",")?.map { number -> number.trim().toInt() }
            ?: throw IllegalStateException(ERROR_WRONG_INPUT)
    }

    fun getBonusLottoNumber(): Int {
        println(GET_BONUS_LOTTO_NUMBER_SCRIPT)
        return readlnOrNull()?.trim()?.toInt() ?: throw IllegalStateException(ERROR_WRONG_INPUT)
    }
}
