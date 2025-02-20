package lotto.domain

data class LottoNumber(
    val value: Int,
) {
    init {
        require(value in Lotto.LOTTO_NUMBER_MIN..Lotto.LOTTO_NUMBER_MAX) { ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_BE_IN_1_TO_45 }
    }

    companion object {
        private const val ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_BE_IN_1_TO_45 = "로또는 1부터 45 사이의 숫자만 가질 수 있습니다."
    }
}
