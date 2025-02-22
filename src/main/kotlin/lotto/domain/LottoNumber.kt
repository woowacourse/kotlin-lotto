package lotto.domain

data class LottoNumber(val lottoNumber: Int) {
    init {
        require(lottoNumber in MIN_RANGE..MAX_RANGE) { LOTTO_NUM_ERROR_MESSAGE }
    }

    companion object {
        private const val MIN_RANGE = 1
        private const val MAX_RANGE = 45
        private const val LOTTO_NUM_ERROR_MESSAGE = "로또 번호는 1에서 45까지의 숫자이다"
    }
}
