package lotto.domain

class Lottery(numbers: List<Int>) {
    private val _numbers: List<LotteryNumber>
    val numbers: List<Int>
        get() = _numbers.map { it.number }

    init {
        this._numbers = numbers.map { LotteryNumber(it) }
        checkLotteryNumbersSize()
        checkNumbersDuplicate()
    }

    fun countMatches(winningLottery: Lottery): Int = winningLottery.numbers.count { numbers.contains(it) }

    fun containBonusNumber(bonusNumber: BonusNumber): Boolean {
        return numbers.contains(bonusNumber.number)
    }

    private fun checkLotteryNumbersSize() {
        require(numbers.size == LOTTERY_NUMBER_SIZE) { LOTTERY_NUMBER_SIZE_ERROR }
    }

    private fun checkNumbersDuplicate() {
        require(numbers.size == numbers.distinct().size) { LOTTERY_NUMBERS_DUPLICATE_ERROR }
    }

    companion object {
        private const val LOTTERY_NUMBER_SIZE = 6
        private const val LOTTERY_NUMBER_SIZE_ERROR = "로또 번호는 6개여야 합니다."
        private const val LOTTERY_NUMBERS_DUPLICATE_ERROR = "6개의 로또번호는 서로 중복되지 않아야 합니다."
    }
}
