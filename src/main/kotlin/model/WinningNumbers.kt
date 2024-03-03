package lotto.model

import model.Lotto
import model.LottoNumber

class WinningNumbers(val winningLotto: Lotto, val bonusNumber: LottoNumber) {
    companion object {
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_DUPLICATE_WINNING_NUMBERS = "${ERROR_PREFIX}당첨번호와 보너스 번호는 중복될 수 없습니다."

        fun of(
            winningLottoValue: List<String>,
            bonusNumberValue: String,
        ): WinningNumbers {
            validateDuplicateWinningNumbers(winningLottoValue, bonusNumberValue)
            val winningLotto = Lotto(LottoNumbers(winningLottoValue.map { LottoNumber(it) }))
            val bonusNumber = LottoNumber(bonusNumberValue)
            return WinningNumbers(winningLotto, bonusNumber)
        }

        private fun validateDuplicateWinningNumbers(
            winningLottoValue: List<String>,
            bonusNumberValue: String,
        ) {
            require(winningLottoValue.all { it != bonusNumberValue }) { ERROR_DUPLICATE_WINNING_NUMBERS }
        }
    }
}
