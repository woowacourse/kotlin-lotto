package domain.model

@JvmInline
value class LottoNumber(val value: Int) {
    init {
        require(value in MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER) { "${value}는 $MINIMUM_LOTTO_NUMBER ~ $MAXIMUM_LOTTO_NUMBER 사이의 정수가 아닙니다." }
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
    }
}
