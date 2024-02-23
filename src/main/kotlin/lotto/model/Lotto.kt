package lotto.model

import lotto.util.LottoRule

class Lotto(private val numbers: Set<Int>) {
    init {
        require(numbers.size == LottoRule.LOTTO_LEN)
        require(numbers.all { it in LottoRule.LOTTO_NUM_RANGE })
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

    fun findRanking(winningNumber: WinningNumber): LottoPrize {
        var rank = LottoPrize.entries.find {
            it.getMatchNumbers() == matchCount(winningNumber)
        } ?: LottoPrize.BOOM
        if (checkSecond(rank, matchBonusNumber(winningNumber))) rank = LottoPrize.SECOND
        return rank
    }

    private fun checkSecond(rank: LottoPrize, matchBonus: Boolean) = rank == LottoPrize.THIRD && matchBonus
}
