package domain.lotto

class Lotto(private val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) { ERROR_MESSAGE_INVALID_LOTTO_SIZE }
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_MESSAGE_INVALID_LOTTO_SIZE = "[ERROR] 로또 번호 6개이어야 합니다."
    }
}
