package lotto.model

@JvmInline
value class LottoNumber(
    val value: Int,
) {
    init {
        require(value in MIN_VALUE..MAX_VALUE) { NUMBER_RANGE_ERROR }
    }

    companion object {
        const val MIN_VALUE = 1
        const val MAX_VALUE = 45
        const val NUMBER_RANGE_ERROR = "[ERROR] 로또 숫자가 범위를 벗어났습니다."

        fun validation(number: Int): ValidationResult {
            if (number !in MIN_VALUE..MAX_VALUE) {
                return ValidationResult.Error.NumberRangeError
            }
            return ValidationResult.Success
        }
    }
}
