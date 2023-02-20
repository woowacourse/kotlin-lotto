package domain.lotto

import util.PREFIX

@JvmInline
value class LottoNumber private constructor(val number: Int) {

    companion object {
        private const val LOTTO_NUMBER_MINIMUM = 1
        private const val LOTTO_NUMBER_MAXIMUM = 45
        val LOTTO_NUMBER_RANGE = LOTTO_NUMBER_MINIMUM..LOTTO_NUMBER_MAXIMUM
        private val cachedLottoNumbers: Map<Int, LottoNumber> = LOTTO_NUMBER_RANGE.associateWith { LottoNumber(it) }

        fun of(number: Int): LottoNumber {
            return cachedLottoNumbers[number] ?: throw IllegalArgumentException("$PREFIX 로또 넘버는 1~45여야합니다.")
        }
    }
}
