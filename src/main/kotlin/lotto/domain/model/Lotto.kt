package lotto.domain.model

import lotto.constants.ErrorMessages
import lotto.domain.value.LottoNumber

class Lotto(
    val lottoNumbers: List<LottoNumber>,
) {
    init {
        require(lottoNumbers.size == NUMBER_OF_LOTTO_NUMBERS) { ErrorMessages.INVALID_NUMBER_OF_LOTTO_NUMBERS }
        require(lottoNumbers.toSet().size == NUMBER_OF_LOTTO_NUMBERS) { ErrorMessages.DUPLICATE_LOTTO_NUMBER }
    }

    fun contains(number: LottoNumber): Boolean = lottoNumbers.contains(number)

    fun count(lotto: Lotto): Int = lottoNumbers.count { lotto.contains(it) }

    companion object {
        private const val NUMBER_OF_LOTTO_NUMBERS = 6

        fun of(vararg numbers: Int): Lotto = Lotto(numbers.map { LottoNumber.from(it) })

        fun create(): Lotto {
            val lottoNumbers =
                LottoNumber.NUMBERS.values
                    .shuffled()
                    .take(NUMBER_OF_LOTTO_NUMBERS)
            return Lotto(lottoNumbers)
        }
    }
}
