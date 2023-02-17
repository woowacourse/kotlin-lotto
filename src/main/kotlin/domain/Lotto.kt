package domain

class Lotto constructor(val numbers: Set<LottoNumber>) {

    init {
        require(numbers.size == LOTTO_SIZE) { ERROR_LOTTO_SIZE }
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "로또 번호는 ${LOTTO_SIZE}개여야 합니다."
    }
}
