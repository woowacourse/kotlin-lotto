package lotto.model

import model.LottoNumber

data class LottoNumbers(val numbers: Set<LottoNumber>) {
    constructor(numbers: List<LottoNumber>) : this(validateLottoNumbers(numbers))

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "로또 번호는 6개여야 합니다."
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 중복될 수 없습니다."

        fun lottoNumbersOf(vararg numbers: Int): LottoNumbers {
            val lottoNumbers = numbers.map { LottoNumber(it) }
            validateLottoNumbers(lottoNumbers)
            return LottoNumbers(lottoNumbers.toSet())
        }

        private fun validateLottoNumbers(numbers: List<LottoNumber>): Set<LottoNumber> {
            require(numbers.size == LOTTO_SIZE) {
                ERROR_LOTTO_SIZE
            }
            val distinctNumbers = numbers.toSet()
            require(distinctNumbers.size == LOTTO_SIZE) {
                ERROR_LOTTO_DUPLICATE
            }
            return distinctNumbers
        }
    }
}
