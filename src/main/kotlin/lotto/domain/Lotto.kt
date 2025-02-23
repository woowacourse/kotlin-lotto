package lotto.domain

class Lotto(val lotto: List<LottoNumber>) {
    val numbers: List<LottoNumber> = lotto.sortedBy { it.number }

    init {
        validateLottoSize()
        validateDuplicateLottoNumber()
    }

    fun toIntList() = numbers.map { it.toInt() }

    private fun validateLottoSize() {
        runCatching { numbers.size == LOTTO_SIZE }
            .onFailure { ERROR_LOTTO_SIZE }
    }

    private fun validateDuplicateLottoNumber() {
        require(numbers.toSet().size == LOTTO_SIZE) { ERROR_NOT_DUPLICATE_LOTTO_NUMBER }
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val ERROR_LOTTO_SIZE = "[ERROR] 로또 번호는 6개여야 합니다."
        const val ERROR_NOT_DUPLICATE_LOTTO_NUMBER = "[ERROR] 로또 번호는 중복될 수 없습니다."
    }
}
