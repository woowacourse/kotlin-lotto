package domain.lotto.number

import util.common.constant.ERROR_PREFIX

@JvmInline
value class LottoNumber(val value: Int) {
    init {
        validateLottoNumberRange()
    }

    private fun validateLottoNumberRange() {
        require(value in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) { ERROR_MESSAGE_LOTTO_NUMBER_OUT_OF_RANGE }
    }

    companion object {
        const val MIN_LOTTO_NUMBER = 1
        const val MAX_LOTTO_NUMBER = 45

        private const val ERROR_MESSAGE_LOTTO_NUMBER_OUT_OF_RANGE = "$ERROR_PREFIX 로또 번호는 1이상 45이하여야 합니다."
        private const val ERROR_MESSAGE_NOT_NUMERIC_LOTTO_NUMBER = "$ERROR_PREFIX 로또 번호는 숫자여야 합니다."

        private val NUMBERS: Map<Int, LottoNumber> = (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).associateWith(::LottoNumber)

        fun from(number: String): LottoNumber {
            val lottoNumber = requireNotNull(number.toIntOrNull()) { ERROR_MESSAGE_NOT_NUMERIC_LOTTO_NUMBER }
            return requireNotNull(NUMBERS[lottoNumber]) { ERROR_MESSAGE_LOTTO_NUMBER_OUT_OF_RANGE }
        }
    }
}
