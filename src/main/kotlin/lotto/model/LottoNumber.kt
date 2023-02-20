package lotto.model

import lotto.view.ERROR_OUT_OF_RANGE

class LottoNumber private constructor(val number: Int) {

    init {
        require(number in START_LOTTO_RANGE..END_LOTTO_RANGE) { ERROR_OUT_OF_RANGE }
    }

    companion object {
        const val START_LOTTO_RANGE = 1
        const val END_LOTTO_RANGE = 45
        private val NUMBERS: Map<Int, LottoNumber> = (START_LOTTO_RANGE..END_LOTTO_RANGE).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException()
        }
    }
}
