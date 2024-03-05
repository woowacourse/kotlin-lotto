package lotto.model

import lotto.exception.Exceptions
import lotto.model.Lotto.Companion.LOTTO_SIZE

class WinningLotto(private val winningNumbers: List<LottoNumber>, private val bonusNumber: LottoNumber) {
    init {
        if (winningNumbers.size != LOTTO_SIZE) {
            throw Exceptions.WinningLottoSizeException()
        }
        if (winningNumbers.toSet().size != LOTTO_SIZE) {
            throw Exceptions.WinningLottoDuplication()
        }
        if (winningNumbers.contains(bonusNumber)) {
            throw Exceptions.BonusNumberDuplicationWithWinningNumber()
        }
    }

    fun judgeRank(lotto: Lotto): Rank {
        val countOfMatch = calculateCountOfMatch(lotto)
        val bonusMatched = checkBonusNumberMatched(lotto)

        return Rank.getRank(countOfMatch, bonusMatched)
    }

    private fun calculateCountOfMatch(lotto: Lotto): Int {
        return winningNumbers.count { winningNumber ->
            lotto.numbers.any { it.number == winningNumber.number }
        }
    }

    private fun checkBonusNumberMatched(lotto: Lotto): Boolean {
        return lotto.numbers.any { it.number == bonusNumber.number }
    }
}
