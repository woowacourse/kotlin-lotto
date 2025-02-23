package lotto.domain

data class LottoNumber(val number: Int) {
    init {
        require(number in MIN_BOUND..MAX_BOUND) { ERROR_OUT_OF_RANGE }
    }

    companion object {
        private const val ERROR_OUT_OF_RANGE = "[ERROR] 로또 번호는 1~45 사이어야 합니다."

        const val MAX_BOUND: Int = 45
        const val MIN_BOUND: Int = 1
    }
}
