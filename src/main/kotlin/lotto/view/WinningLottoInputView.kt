package lotto.view

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.WinningLotto
import lotto.view.util.retryWhileNoException

class WinningLottoInputView {
    fun readWinningLotto(): WinningLotto {
        val winningLottoNumbers = readWinningLottoNumbers()
        val bonusNumber = readBonusNumber()
        return WinningLotto(winningLottoNumbers, bonusNumber)
    }

    private fun readWinningLottoNumbers(): Lotto =
        retryWhileNoException {
            println("지난 주 당첨 번호를 입력해 주세요.")
            val winningLottoNumbers = readln()
            validateDigit(winningLottoNumbers)

            val splittedWinningNumbers = winningLottoNumbers.split(LOTTO_NUMBER_DELIMITER).map { it.toInt() }
            validateNotDuplicate(splittedWinningNumbers)
            Lotto(splittedWinningNumbers)
        }

    private fun readBonusNumber(): LottoNumber =
        retryWhileNoException {
            println("보너스 볼을 입력해 주세요.")
            val bonusNumber = readln()
            validateDigit(bonusNumber)
            LottoNumber(bonusNumber.toInt())
        }.also { println() }

    private fun validateDigit(lottoNumbers: String) =
        require(lottoNumbers.split(LOTTO_NUMBER_DELIMITER).all { isPositiveDigit(it) }) {
            "올바른 로또 번호를 입력해 주세요."
        }

    private fun isPositiveDigit(it: String) = it.toIntOrNull() != null && it.toInt() >= MINIMUM_DIGIT

    private fun validateNotDuplicate(winningLottoNumber: List<Int>) =
        require(winningLottoNumber.toSet().size == winningLottoNumber.size) {
            "올바른 로또 번호를 입력해 주세요."
        }

    companion object {
        private const val LOTTO_NUMBER_DELIMITER = ", "
        private const val MINIMUM_DIGIT = 0
    }
}
