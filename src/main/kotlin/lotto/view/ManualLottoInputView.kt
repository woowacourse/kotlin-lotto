package lotto.view

import lotto.model.Lotto
import lotto.view.util.retryWhileNoException

class ManualLottoInputView {
    fun readManualLottoNumbers(manualCount: Int): List<Lotto> =
        retryWhileNoException {
            println("수동으로 구매할 번호를 입력해 주세요.")
            List(manualCount) { createLotto() }
        }.also { println() }

    private fun createLotto(): Lotto {
        val userInput = readln()

        validateDigit(userInput)
        val numbers = userInput.split(LOTTO_NUMBER_DELIMITER).map { it.toInt() }
        validateNotDuplicate(numbers)

        return Lotto(numbers)
    }

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
