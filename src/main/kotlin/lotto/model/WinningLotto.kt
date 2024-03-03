package lotto.model

import lotto.exception.ErrorCode
import lotto.exception.ExceptionHandler.handleException
import lotto.util.LottoConstants

class WinningLotto(private val winningNumbers: List<LottoNumber>, private val bonusNumber: LottoNumber) {
    init {
        handleException(ErrorCode.INVALID_WINNING_NUMBERS_SIZE) { winningNumbers.size == LottoConstants.SIZE }
        handleException(ErrorCode.INVALID_WINNING_NUMBERS_DUPLICATE) { winningNumbers.toSet().size == LottoConstants.SIZE }
        handleException(ErrorCode.INVALID_BONUS_NUMBER_DUPLICATE) { !winningNumbers.contains(bonusNumber) }
    }

    fun calculateCountOfMatch(lotto: Lotto): Int {
        return winningNumbers.toSet().intersect(lotto.numbers).size
    }

    fun checkBonusNumberMatched(lotto: Lotto): Boolean {
        return lotto.numbers.any { it == bonusNumber }
    }
}
