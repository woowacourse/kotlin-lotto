package lotto.view

import lotto.model.Lotto
import lotto.model.PurchaseInfo

class InputView {

    fun readPurchasePrice() = retryWhileNoException {
        println("구입금액을 입력해 주세요.")
        val purchasePrice = readln()
        PurchaseInfo(purchasePrice)
    }

    fun readWinningLottoNumbers() = retryWhileNoException {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val winningLottoNumbers = readln()
        validateDigit(winningLottoNumbers)
        Lotto(winningLottoNumbers.split(", ").map { it.toInt() })
    }

    private fun validateDigit(lottoNumbers: String) =
        require(lottoNumbers.split(", ").all { it.toIntOrNull() != null }) {
            "올바른 로또 번호를 입력해 주세요."
        }

    private fun <T> retryWhileNoException(action: () -> T): T {
        return try {
            action()
        } catch (e: IllegalArgumentException) {
            println("$ERROR_MESSAGE ${e.localizedMessage}")
            action()
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "[ERROR]"
    }

}
