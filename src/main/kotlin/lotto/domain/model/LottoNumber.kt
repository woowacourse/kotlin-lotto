package lotto.domain.model

data class LottoNumber(
    val value: Int,
) {
    init {
        require(value in VALID_RANGE) { RANGE_ERROR }
    }

    companion object {
        private val VALID_RANGE = 1..45
        private const val RANGE_ERROR = "[ERROR] 범위 외의 값입니다."

        fun valueOf(value: Int): LottoNumber = LottoNumber(value)

        fun valueOfOrNull(value: Int): LottoNumber? = runCatching { valueOf(value) }.getOrNull()
    }
}
