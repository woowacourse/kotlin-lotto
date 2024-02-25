package domain.model

@JvmInline
value class LottoNumber(val value: Int) {
    init {
        require(value in MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER) { "당첨번호는 $MINIMUM_LOTTO_NUMBER ~ $MAXIMUM_LOTTO_NUMBER 사이의 정수여야 합니다." }
    }

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
    }
}
