package lotto

class WinningNumber(
    val numbers: List<Int>,
) {
    init {
        require(numbers.size == NUMBER_COUNT) { ERROR_COUNT_OF_NUMBERS }
        require(numbers.size == numbers.distinct().size) { ERROR_DUPLICATED_NUMBERS }
        require(numbers.all { number -> number in MIN_NUMBER..MAX_NUMBER }) { ERROR_OUT_OF_RANGE_NUMBERS }
    }

    companion object {
        private const val ERROR_COUNT_OF_NUMBERS = "당첨 번호는 6개의 숫자입니다."
        private const val ERROR_DUPLICATED_NUMBERS = "중복된 숫자가 존재합니다."
        private const val ERROR_OUT_OF_RANGE_NUMBERS = "당첨 번호는 1 ~ 45 사이의 숫자입니다."
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 45
        private const val NUMBER_COUNT = 6
    }
}
