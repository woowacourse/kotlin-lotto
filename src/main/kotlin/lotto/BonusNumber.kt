package lotto

class BonusNumber(
    val number: Int,
) {
    init {
        require(number in MIN_NUMBER..MAX_NUMBER) { ERROR_OUT_OF_RANGE_NUMBERS }
    }

    companion object {
        private const val ERROR_OUT_OF_RANGE_NUMBERS = "보너스 번호는 1 ~ 45 사이의 숫자입니다."
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 45
    }
}
