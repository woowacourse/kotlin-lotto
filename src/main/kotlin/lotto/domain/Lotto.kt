package lotto.domain

class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { LOTTO_SIZE_ERROR }
        require(numbers.all { it in 1..45 }) { LOTTO_RANGE_ERROR }
        require(numbers.distinct().size == 6) { LOTTO_DUPLICATE_ERROR }
    }

    fun countMatchingNumbers(winningNumbers: List<Int>): Int {
        return numbers.count { number -> winningNumbers.contains(number) }
    }

    fun checkMatchingBonusNumber(bonusNumber: Int): Boolean {
        return numbers.contains(bonusNumber)
    }

    companion object {
        private const val LOTTO_SIZE_ERROR = "로또 번호는 여섯 개여야 합니다."
        private const val LOTTO_RANGE_ERROR = "로또 번호는 1과 45 사이여야 합니다."
        private const val LOTTO_DUPLICATE_ERROR = "로또 번호는 중복되면 안됩니다."
    }
}