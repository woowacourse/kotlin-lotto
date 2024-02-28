package lotto.model

data class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) {
            ERROR_LOTTO_SIZE
        }
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "로또 번호는 ${LOTTO_SIZE}개여야 합니다."
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 중복될 수 없습니다."

        fun lottoNumbersOf(numbers: List<String>): Lotto {
            validateNumbersSize(numbers)
            validateDuplicateNumbers(numbers)
            return Lotto(numbers.map { LottoNumber.from(it) }.toSet())
        }

        private fun validateDuplicateNumbers(numbers: List<String>) {
            require(numbers.distinct().size == numbers.size) {
                ERROR_LOTTO_DUPLICATE
            }
        }

        private fun validateNumbersSize(numbers: List<String>) {
            require(numbers.size == LOTTO_SIZE) {
                ERROR_LOTTO_SIZE
            }
        }
    }

    fun getMatchCount(winningNumbers: Lotto): Int = numbers.count { it in winningNumbers.numbers }

    fun getMatchBonus(bonusNumber: LottoNumber): Boolean = numbers.contains(bonusNumber)
}
