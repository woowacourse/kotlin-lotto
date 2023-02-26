package lotto.domain.model

import lotto.view.OutputView

class WinningNumbers(val winningLotto: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(!checkBonusNumberAvailable(winningLotto, bonusNumber.number)) { BONUS_NUMBER_DUPLICATE_ERROR }
    }

    companion object {
        fun checkBonusNumberAvailable(winningLotto: Lotto, bonusNumber: Int): Boolean {
            if (winningLotto.numbers.any { lottoNumber -> lottoNumber.number == bonusNumber }) {
                println(OutputView.ERROR_PREFIX + BONUS_NUMBER_DUPLICATE_ERROR)
                return true
            }
            return false
        }

        const val BONUS_NUMBER_DUPLICATE_ERROR = "당첨번호와 보너스 번호는 중복되면 안됩니다."
    }
}
