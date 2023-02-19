package lotto.domain

import lotto.constant.LOTTO_MAXIMUM_NUMBER
import lotto.constant.LOTTO_MINIMUM_NUMBER

class LottoNumber private constructor(val value: Int) {
    companion object {
        private val NUMBERS: Map<Int, LottoNumber> =
            (LOTTO_MINIMUM_NUMBER..LOTTO_MAXIMUM_NUMBER).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR)
        }

        private const val LOTTO_NUMBER_RANGE_ERROR = "로또 숫자는 1 이상 45 이하 입니다."
    }
}
