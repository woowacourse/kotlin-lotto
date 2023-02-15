package domain.lotto

open class Lotto(numbers: List<LottoNumber>) : List<LottoNumber> by numbers {
    init {
        validateLottoNumbers()
    }

    private fun validateLottoNumbers() {
        validateLottoNumbersSize()
        validateLottoNumbersDuplication()
    }

    private fun validateLottoNumbersSize() {
        require(size == LOTTO_SIZE) { ERROR_MESSAGE_INVALID_LOTTO_SIZE }
    }

    private fun validateLottoNumbersDuplication() {
        check(distinctBy { it.value }.size == LOTTO_SIZE) { ERROR_MESSAGE_DUPLICATED_LOTTO_NUMBER }
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_MESSAGE_INVALID_LOTTO_SIZE = "[ERROR] 로또 번호 6개이어야 합니다."
        private const val ERROR_MESSAGE_DUPLICATED_LOTTO_NUMBER = "[ERROR] 로또 번호는 중복되지 않아야 합니다."
    }
}
