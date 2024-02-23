package model

data class LotteryNumber(val number: Int) {
    companion object {
        private const val MIN_LOTTERY_NUMBER = 1
        private const val MAX_LOTTERY_NUMBER = 45
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val EXCEPTION_INVALID_RANGE = "보너스 번호는 $MIN_LOTTERY_NUMBER ~ $MAX_LOTTERY_NUMBER 사이 숫자여야 합니다"

        private fun Int.validateRange(): Int {
            require((MIN_LOTTERY_NUMBER..MAX_LOTTERY_NUMBER).contains(this)) { EXCEPTION_INVALID_RANGE }
            return this
        }

        private fun String.toInt(): Int {
            return this.toIntOrNull() ?: throw IllegalArgumentException(EXCEPTION_IS_NOT_NUMBER)
        }

        fun fromInput(input: String): LotteryNumber {
            val validateRange = input.trim().toInt().validateRange()
            return LotteryNumber(validateRange)
        }
    }
}
