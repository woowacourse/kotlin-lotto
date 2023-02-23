package lotto.entity

data class LottoNumber private constructor(val value: Int) {
    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private val LOTTO_RANGE = (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER)
        private const val ERROR_MESSAGE_RANGE_1_TO_45 = "당첨 번호는 1에서 45 사이의 숫자여야 합니다. 입력된 당첨 번호는 %d 입니다."
        val lottoNumberCache = LOTTO_RANGE.map { LottoNumber(it) }

        fun from(value: Int): LottoNumber {
            require(value in LOTTO_RANGE) { ERROR_MESSAGE_RANGE_1_TO_45.format(value) }
            return lottoNumberCache[value - 1]
        }
    }
}
