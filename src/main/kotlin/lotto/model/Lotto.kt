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

    fun findRanking(matchCount: Int, matchBonus: Boolean): LottoPrize {
        var rank = LottoPrize.entries.find {
            it.getMatchNumbers() == matchCount
        } ?: LottoPrize.BOOM
        if (checkSecond(rank, matchBonus)) rank = LottoPrize.SECOND
        return rank
    }

    private fun checkSecond(rank: LottoPrize, matchBonus: Boolean) = rank == LottoPrize.THIRD && matchBonus
}
