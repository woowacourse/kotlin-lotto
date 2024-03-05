package lotto.model

import lotto.exception.ErrorCode
import lotto.exception.ExceptionHandler.handleException
import lotto.model.Lotto.Companion.LOTTO_SIZE

class WinningLotto(private val winningNumbers: List<LottoNumber>, private val bonusNumber: LottoNumber) {
    init {
        handleException(ErrorCode.INVALID_WINNING_NUMBERS_SIZE) { winningNumbers.size == LOTTO_SIZE }
        handleException(ErrorCode.INVALID_WINNING_NUMBERS_DUPLICATE) { winningNumbers.toSet().size == LOTTO_SIZE }
        handleException(ErrorCode.INVALID_BONUS_NUMBER_DUPLICATE) { !winningNumbers.contains(bonusNumber) }
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
