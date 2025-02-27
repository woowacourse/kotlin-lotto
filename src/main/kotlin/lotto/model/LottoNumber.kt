package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_MAX_RANGE
import lotto.model.Lotto.Companion.LOTTO_NUMBER_MIN_RANGE

class LottoNumber(
    val number: Int,
) {
    init {
        validateLottoNumbersRange(number)
    }

    private fun validateLottoNumbersRange(number: Int) {
        require(number in LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE) {
            "[ERROR] 로또 번호의 범위는 $LOTTO_NUMBER_MIN_RANGE 이상 $LOTTO_NUMBER_MAX_RANGE 이하여야 합니다."
        }
    }
}
