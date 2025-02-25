package lotto.domain

class Lotto(randomNumbers: List<LottoNumber>) {
    val numbers: List<LottoNumber> = randomNumbers.sortedBy { it.number }

    init {
        require(numbers.size == MAX_LOTTO_SIZE) { ERROR_NOT_SIX }
        require(numbers.toSet().size == numbers.size) { ERROR_NOT_DUPLICATE_NUMBER }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Lotto) return false
        return numbers.toSet() == other.numbers.toSet()
    }

    override fun hashCode(): Int {
        return numbers.toSet().hashCode()
    }

    companion object {
        private const val ERROR_NOT_SIX = "[ERROR] 로또 번호는 6개여야 합니다."
        private const val ERROR_NOT_DUPLICATE_NUMBER = "[ERROR] 로또 번호는 중복될 수 없습니다."

        const val MAX_LOTTO_SIZE = 6
    }
}
