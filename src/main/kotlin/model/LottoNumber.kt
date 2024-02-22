package model

data class LottoNumber(val number: Int) {
    constructor(input: String) : this(
        input.trim().toIntOrNull() ?: throw IllegalArgumentException(
            EXCEPTION_IS_NOT_NUMBER,
        ),
    )

    init {
        require(validateRange(number)) { EXCEPTION_INVALID_RANGE }
    }

    private fun validateRange(number: Int) = (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).contains(number)

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val EXCEPTION_INVALID_RANGE = "보너스 번호는 $MIN_LOTTO_NUMBER ~ $MAX_LOTTO_NUMBER 사이 숫자여야 합니다"
    }
}
