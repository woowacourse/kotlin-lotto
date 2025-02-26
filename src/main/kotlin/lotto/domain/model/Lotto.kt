package lotto.domain.model

import lotto.domain.value.LottoNumber

class Lotto(
    val lottoNumbers: List<LottoNumber>,
) {
    init {
        require(lottoNumbers.size == NUMBER_OF_LOTTO_NUMBERS) { ERROR_NUMBER_OF_LOTTO_NUMBERS }
        require(lottoNumbers.toSet().size == NUMBER_OF_LOTTO_NUMBERS) { ERROR_DUPLICATE_LOTTO_NUMBER }
    }

    fun contains(number: LottoNumber): Boolean = lottoNumbers.contains(number)

    fun count(other: Lotto): Int = lottoNumbers.count { other.contains(it) }

    companion object {
        private const val NUMBER_OF_LOTTO_NUMBERS = 6
        private const val ERROR_NUMBER_OF_LOTTO_NUMBERS = "[ERROR] 로또 번호는 6개여야 합니다."
        private const val ERROR_DUPLICATE_LOTTO_NUMBER = "[ERROR] 로또 번호는 중복되면 안됩니다."

        fun of(numbers: List<Int>): Lotto = Lotto(numbers.map { LottoNumber.from(it) })

        fun create(): Lotto {
            val lottoNumbers =
                LottoNumber.NUMBERS.values
                    .shuffled()
                    .take(NUMBER_OF_LOTTO_NUMBERS)
            return Lotto(lottoNumbers)
        }
    }
}
