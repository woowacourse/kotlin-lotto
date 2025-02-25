package lotto.model

@JvmInline
value class LottoNumber(
    val value: Int,
) {
    companion object {
        const val MIN_VALUE = 1
        const val MAX_VALUE = 45
        private const val RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호는 1~45 사이여야 합니다."

        fun create(value: Int): LottoNumber? {
            if (value !in MIN_VALUE..MAX_VALUE) {
                return null
            }
            return LottoNumber(value)
        }
    }
}
