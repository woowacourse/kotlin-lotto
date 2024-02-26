package lotto.model

import lotto.util.Constant

class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == Constant.LOTTO_LEN) {
            "로또는 6개의 숫자로 이루어져있습니다."
        }
    }

    fun matchCount(winningNumber: WinningNumber): Int {
        return winningNumber
            .getWinning()
            .numbers
            .intersect(numbers)
            .size
    }

    fun matchBonusNumber(winningNumber: WinningNumber): Boolean {
        return numbers.contains(winningNumber.getBonusNumber())
    }

    fun findRanking(winningNumber: WinningNumber): LottoPrize {
        var rank =
            LottoPrize.entries.find {
                it.getMatchNumbers() == matchCount(winningNumber)
            } ?: LottoPrize.BOOM
        if (checkSecond(rank, matchBonusNumber(winningNumber))) {
            rank = LottoPrize.SECOND
        }
        return rank
    }

    fun contains(number: LottoNumber): Boolean {
        return number in numbers
    }

    private fun checkSecond(
        rank: LottoPrize,
        matchBonus: Boolean,
    ) = rank == LottoPrize.THIRD && matchBonus
}
