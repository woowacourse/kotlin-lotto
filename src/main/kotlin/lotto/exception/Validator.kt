package lotto.exception

import lotto.domain.Lotto

object Validator {
    private const val MONEY_NOT_NUMBER_ERROR = "금액은 숫자여야 합니다."
    private const val MONEY_NEGATIVE_NUMBER_ERROR = "금액은 양수여야 합니다."
    private const val MONEY_UNIT_ERROR = "금액은 1000원 단위여야 합니다."
    private const val MANUAL_LOTTO_INPUT_ERROR = "수동 로또 구입 개수는 숫자여야 합니다."
    private const val MANUAL_LOTTO_COUNT_ERROR = "수동 로또의 개수는 0이상 구매 로또 개수 이하여야 합니다."
    private const val BONUS_NOT_NUMBER_ERROR = "보너스 볼은 숫자여야 합니다."

    fun checkInputMoney(input: String) {
        val number = input.toIntOrNull()
        require(number != null) { MONEY_NOT_NUMBER_ERROR }
        require(number > 0) { MONEY_NEGATIVE_NUMBER_ERROR }
        require(number % Lotto.MONEY_UNIT == 0) { MONEY_UNIT_ERROR }
    }

    fun checkInputBonusNumber(input: String) {
        require(input.toIntOrNull() != null) { BONUS_NOT_NUMBER_ERROR }
    }

    fun checkInputManualLottoCount(input: String, lottoCount: Int) {
        val count = input.toIntOrNull()
        require(count != null) { MANUAL_LOTTO_INPUT_ERROR }
        require(count in 0..lottoCount) { MANUAL_LOTTO_COUNT_ERROR }
    }
}
