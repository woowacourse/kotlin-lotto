package lotto.view

class InputView {
    fun readPayAmount(): Int {
        println(ALERT_READ_PAY_INPUT)
        return readSingleNumber()
    }

    fun readManualLottoQuantity(): Int {
        println(ALERT_READ_BUY_MANUAL_LOTTO_QUANTITY)
        return readSingleNumber()
    }

    fun showManualLottoNumbersAlert() {
        println(ALERT_READ_BUY_MANUAL_LOTTO_NUMBERS)
    }

    fun readSingleManualLottoNumbers(): List<Int> = readSingleLottoNumbers()

    fun readWinLottoNumbers(): List<Int> {
        println(ALERT_READ_PREV_WEEK_WIN_NUMBERS)
        return readSingleLottoNumbers()
    }

    fun readBonusBallNumber(): Int {
        println(ALERT_READ_PREV_WEEK_BONUS_NUMBER)
        return readSingleNumber()
    }

    private fun readSingleLottoNumbers(): List<Int> =
        runCatching {
            readln().split(',').map { it.trim().toInt() }
        }.getOrElse { throw IllegalArgumentException(ERROR_READ_PAY_INPUT) }

    private fun readSingleNumber(): Int =
        runCatching { readln().toInt() }.getOrElse { throw IllegalArgumentException(ERROR_READ_PAY_INPUT) }

    companion object {
        private const val ALERT_READ_PAY_INPUT = "구입금액을 입력해 주세요."
        private const val ALERT_READ_BUY_MANUAL_LOTTO_QUANTITY = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val ALERT_READ_BUY_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
        private const val ALERT_READ_PREV_WEEK_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val ALERT_READ_PREV_WEEK_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val ERROR_READ_PAY_INPUT = "숫자 형식이 올바르지 않습니다."
    }
}
