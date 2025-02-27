package lotto.domain.model

@JvmInline
value class LottoNumber(private val value: Int) {
    init {
        require(value in MIN_VALUE..MAX_VALUE) {
            RANGE_ERROR_MESSAGE
        }
    }

    override fun toString(): String = value.toString()

    companion object {
        const val MIN_VALUE = 1
        const val MAX_VALUE = 45
        private const val RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호는 1~45 사이여야 합니다."
    }
}
