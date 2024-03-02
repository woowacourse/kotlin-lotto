package model.lottery

class LotteryNumber private constructor(val number: Int) {
    init {
        require(number in LOTTERY_NUMBER_RANGE) { ERROR_LOTTERY_OUT_OF_RANGE }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LotteryNumber

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }

    override fun toString(): String {
        return "LotteryNumber(number=$number)"
    }

    companion object {
        private const val MIN_LOTTERY_NUMBER = 1
        private const val MAX_LOTTERY_NUMBER = 45
        val LOTTERY_NUMBER_RANGE: IntRange = MIN_LOTTERY_NUMBER..MAX_LOTTERY_NUMBER

        private val NUMBERS: Map<Int, LotteryNumber> =
            LOTTERY_NUMBER_RANGE.associateWith(::LotteryNumber)

        fun of(input: Int): LotteryNumber = NUMBERS[input] ?: throw IllegalArgumentException(ERROR_LOTTERY_OUT_OF_RANGE)

        private const val ERROR_LOTTERY_OUT_OF_RANGE = "로또 번호는 $MIN_LOTTERY_NUMBER 이상 $MAX_LOTTERY_NUMBER 이하의 숫자여야 합니다."
    }
}
