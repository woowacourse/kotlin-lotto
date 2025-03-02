package lotto.view

class InputView {
    fun readPayAmount(): Int {
        println(ALERT_READ_PAY_INPUT)
        return runCatching { readln().toInt() }.getOrElse { throw IllegalArgumentException(ERROR_READ_PAY_INPUT) }
    }

    fun readManualLottoQuantity(): Int {
        println(ALERT_READ_BUY_MANUAL_LOTTO_QUANTITY)
        return runCatching { readln().toInt() }.getOrElse { throw IllegalArgumentException(ERROR_READ_PAY_INPUT) }
    }

    companion object {
        private const val ALERT_READ_PAY_INPUT = "구입금액을 입력해 주세요."
        private const val ALERT_READ_BUY_MANUAL_LOTTO_QUANTITY = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val ERROR_READ_PAY_INPUT = "숫자 형식이 올바르지 않습니다."
    }
}
