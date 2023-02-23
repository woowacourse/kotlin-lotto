package lotto.view

import lotto.utils.inputNullHandler

object InputView {
    private const val GET_PURCHASE_MONEY_SCRIPT = "구입금액을 입력해 주세요."
    private const val GET_MANUAL_LOTTO_COUNT_SCRIPT = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val GET_MANUAL_LOTTO_NUMBER_LINES_SCRIPT = "수동으로 구매할 번호를 입력해 주세요."
    private const val GET_MAIN_LOTTO_NUMBERS_SCRIPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val GET_BONUS_LOTTO_NUMBER_SCRIPT = "보너스 볼을 입력해 주세요."
    private const val MAIN_LOTTO_NUMBERS_DELIMITER = ','
    private const val ERROR_NOT_NUMBER = "숫자를 입력해야 합니다."
    private const val ERROR_NOT_COMMA_NUMBER = "숫자와 콤마만 입력해야 합니다."

    fun getPurchaseMoney(): Int {
        println(GET_PURCHASE_MONEY_SCRIPT)
        return readln().trim().toIntOrNull() ?: inputNullHandler(ERROR_NOT_NUMBER, ::getPurchaseMoney)
    }

    fun getManualLottoCount(): Int {
        println(GET_MANUAL_LOTTO_COUNT_SCRIPT)
        return readln().trim().toIntOrNull() ?: inputNullHandler(ERROR_NOT_NUMBER, ::getManualLottoCount)
    }

    fun getManualNumberLines(lottoCount: Int): List<List<Int>> {
        println(GET_MANUAL_LOTTO_NUMBER_LINES_SCRIPT)
        return List(lottoCount) { getLottoNumbers() }
    }

    fun getMainLottoNumbers(): List<Int> {
        println(GET_MAIN_LOTTO_NUMBERS_SCRIPT)
        return getLottoNumbers()
    }

    private fun getLottoNumbers(): List<Int> {
        val lottoNumbers = readln().split(MAIN_LOTTO_NUMBERS_DELIMITER)
            .map {
                it.trim().toIntOrNull() ?: return inputNullHandler(ERROR_NOT_COMMA_NUMBER, ::getLottoNumbers)
            }
        return lottoNumbers
    }

    fun getBonusLottoNumber(): Int {
        println(GET_BONUS_LOTTO_NUMBER_SCRIPT)
        return readln().trim().toIntOrNull() ?: inputNullHandler(ERROR_NOT_NUMBER, ::getBonusLottoNumber)
    }
}
