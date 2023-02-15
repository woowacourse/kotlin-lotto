package lotto.domain

class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.size == 6)
        require(numbers.all { it in 1..45 })
        require(numbers.distinct().size == 6)
    }

    fun countMatchingNumbers(winningNumbers: List<Int>): Int {
        return numbers.count { number -> winningNumbers.contains(number) }
    }

    fun checkMatchingBonusNumber(bonusNumber: Int): Boolean {
        return numbers.contains(bonusNumber)
    }
}