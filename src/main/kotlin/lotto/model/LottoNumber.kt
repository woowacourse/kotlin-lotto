package lotto.model

class LottoNumber private constructor(val value: Int) {

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private val LOTTO_NUMBERS = (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).associateWith { LottoNumber(it) }

        fun from(number: Int): LottoNumber {
            return LOTTO_NUMBERS[number]
                ?: throw IllegalArgumentException("${number}는 $MINIMUM_LOTTO_NUMBER ~ $MAXIMUM_LOTTO_NUMBER 사이의 정수가 아닙니다.")
        }
    }
}
