package lotto.model

import lotto.util.Constant

class Lotto(private val numbers: Set<Int>) {
    init {
        require(numbers.size == Constant.LOTTO_LEN)
        require(numbers.all { it in Constant.LOTTO_NUM_RANGE })
    }

    fun getNumbers(): Set<Int> {
        return numbers
    }

    fun matchCount(winningNumber: WinningNumber): Int {
        return winningNumber
            .getWinning()
            .getNumbers()
            .intersect(numbers)
            .size
    }

    fun matchBonusNumber(winningNumber: WinningNumber): Boolean {
        return numbers.contains(winningNumber.getBonusNumber())
    }
}
