package lotto.domain.model

import lotto.view.OutputView

class LottoNumber private constructor(val number: Int) {
    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        private val NUMBERS: Map<Int, LottoNumber> =
            (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).associateWith(::LottoNumber)
        const val LOTTO_NUMBER_RANGE_ERROR = "로또 번호는 ${MINIMUM_LOTTO_NUMBER}과 $MAXIMUM_LOTTO_NUMBER 사이여야 합니다."
        fun checkLottoNumberAvailable(number: Int): Boolean {
            if (number !in 1..45) {
                println(OutputView.ERROR_PREFIX + LOTTO_NUMBER_RANGE_ERROR)
                return false
            }
            return true
        }

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR)
        }
    }
}
