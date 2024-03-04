package lotto.model.lottery

class LotteryNumber private constructor(private val number: Int) {
    override fun toString() = number.toString()

    companion object {
        private const val MIN_LOTTERY_NUMBER = 1
        private const val MAX_LOTTERY_NUMBER = 45
        private val NUMBERS: MutableMap<Int, LotteryNumber> = mutableMapOf()
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val EXCEPTION_INVALID_RANGE = "로또 번호는 $MIN_LOTTERY_NUMBER ~ $MAX_LOTTERY_NUMBER 사이 숫자여야 합니다"

        private fun Int.validateRange(): Int {
            require((MIN_LOTTERY_NUMBER..MAX_LOTTERY_NUMBER).contains(this)) { EXCEPTION_INVALID_RANGE }
            return this
        }

        fun from(input: Int): LotteryNumber {
            val number = input.validateRange()
            if (!NUMBERS.contains(number)) {
                NUMBERS[number] = LotteryNumber(number)
            }

            return NUMBERS[number] ?: throw IllegalArgumentException(EXCEPTION_INVALID_RANGE)
        }
    }
}
