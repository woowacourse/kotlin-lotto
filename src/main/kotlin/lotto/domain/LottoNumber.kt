package lotto.domain

data class LottoNumber(val number: Int) {
    init {
        require(number in 1..45) { ERROR_OUT_OF_RANGE }
    }

    companion object {
        private const val ERROR_OUT_OF_RANGE = "[ERROR] 로또 번호는 1~45 사이어야 합니다."
    }
}
