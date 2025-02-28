package lotto

data class LottoNumber private constructor(
    val value: Int,
) {
    companion object {
        private val VALID_RANGE = 1..45
        private const val RANGE_ERROR = "[ERROR] 범위 외의 값입니다."

        fun valueOf(value: Int): LottoNumber {
            require(value in VALID_RANGE) { RANGE_ERROR }
            return LottoNumber(value)
        }

        fun createOrNull(value: Int): LottoNumber? {
            if (validation(value)) return LottoNumber(value)
            return null
        }

        private fun validation(value: Int): Boolean = value in VALID_RANGE
    }
}
