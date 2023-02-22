package lotto.exception

object Validator {

    private const val MANUAL_LOTTO_INPUT_ERROR = "수동 로또 구입 개수는 숫자여야 합니다."
    private const val MANUAL_LOTTO_COUNT_ERROR = "수동 로또의 개수는 0이상 구매 로또 개수 이하여야 합니다."
    private const val BONUS_NOT_NUMBER_ERROR = "보너스 볼은 숫자여야 합니다."

    fun checkInputBonusNumber(input: String) {
        require(input.toIntOrNull() != null) { BONUS_NOT_NUMBER_ERROR }
    }

    fun checkInputManualLottoCount(input: String, lottoCount: Int) {
        val count = input.toIntOrNull()
        require(count != null) { MANUAL_LOTTO_INPUT_ERROR }
        require(count in 0..lottoCount) { MANUAL_LOTTO_COUNT_ERROR }
    }
}
