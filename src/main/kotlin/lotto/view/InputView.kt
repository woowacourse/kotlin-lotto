package lotto.view

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.view.util.retryWhileNoException

class InputView {
    fun readPurchasePrice(): Int =
        retryWhileNoException {
            println(INPUT_PURCHASE_PRICE)
            val purchasePrice = readln()
            validateDigit(purchasePrice, INVALID_PURCHASE_PRICE)
            purchasePrice.toInt()
        }

    fun readWinningLottoNumbers(): Lotto =
        retryWhileNoException {
            println(INPUT_WINNING_LOTTO_NUMBERS)
            val winningLottoNumbers = readln()
            validateDigit(winningLottoNumbers, INVALID_LOTTO_NUMBER)

            val splittedWinningNumbers = winningLottoNumbers.split(LOTTO_NUMBER_DELIMITER).map { it.toInt() }
            validateNotDuplicate(splittedWinningNumbers)
            Lotto(splittedWinningNumbers.toSet())
        }

    fun readBonusNumber(): LottoNumber =
        retryWhileNoException {
            println(INPUT_BONUS_NUMBER)
            val bonusNumber = readln()
            validateDigit(bonusNumber, INVALID_BONUS_NUMBER)
            LottoNumber(bonusNumber.toInt())
        }

    private fun validateDigit(
        lottoNumbers: String,
        errorMessage: String,
    ) = require(lottoNumbers.split(LOTTO_NUMBER_DELIMITER).all { isPositiveDigit(it) }) {
        errorMessage
    }

    private fun validateNotDuplicate(winningLottoNumber: List<Int>) =
        require(winningLottoNumber.toSet().size == winningLottoNumber.size) {
            INVALID_LOTTO_NUMBER
        }

    private fun isPositiveDigit(it: String) = it.toIntOrNull() != null && it.toInt() > MINIMUM_DIGIT

    companion object {
        private const val INVALID_PURCHASE_PRICE = "올바른 금액을 입력해 주세요."
        private const val INVALID_LOTTO_NUMBER = "올바른 로또 번호를 입력해 주세요."
        private const val INVALID_BONUS_NUMBER = "올바른 보너스 번호를 입력해 주세요."
        private const val LOTTO_NUMBER_DELIMITER = ", "
        private const val INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val MINIMUM_DIGIT = 0
    }
}
