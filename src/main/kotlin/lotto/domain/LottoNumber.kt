package lotto.domain

data class LottoNumber(
    private val value: Int,
) {
    init {
        require(value in MIN..MAX) { ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_BE_IN_1_TO_45 }
    }

    companion object {
        const val MIN = 1
        const val MAX = 45
        private const val ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_BE_IN_1_TO_45 = "로또는 1부터 45 사이의 숫자만 가질 수 있습니다."
    }
}
