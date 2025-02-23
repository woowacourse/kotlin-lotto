package lotto.domain

data class LottoNumber(
    val value: Int,
) {
    init {
        require(value in MIN..MAX) { ERROR_MESSAGE_LOTTO_NUMBER_NOT_IN_RAGE }
    }

    companion object {
        const val MIN = 1
        const val MAX = 45
        val RANGE = (MIN..MAX)
        private const val ERROR_MESSAGE_LOTTO_NUMBER_NOT_IN_RAGE = "로또는 1부터 45 사이의 숫자만 가질 수 있습니다."
    }
}
