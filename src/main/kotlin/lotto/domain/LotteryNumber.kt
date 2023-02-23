package lotto.domain

import java.lang.IllegalArgumentException

class LotteryNumber private constructor(val number: Int) {
    companion object {
        private const val LOTTERY_NUMBER_LOWER_BOUNDARY = 1
        private const val LOTTERY_NUMBER_UPPER_BOUNDARY = 45
        private const val NUMBER_ERROR = "로또 번호는 1 이상 45 이하의 숫자여야 합니다."
        private val NUMBERS: Map<Int, LotteryNumber> =
            (LOTTERY_NUMBER_LOWER_BOUNDARY..LOTTERY_NUMBER_UPPER_BOUNDARY).associateWith(::LotteryNumber)

        fun from(number: Int): LotteryNumber {
            return NUMBERS[number] ?: throw IllegalArgumentException(NUMBER_ERROR)
        }

        fun all(): List<LotteryNumber> {
            return NUMBERS.values.toList()
        }
    }
}
