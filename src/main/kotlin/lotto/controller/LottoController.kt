package lotto.controller

import lotto.constants.StringConstants
import lotto.model.PurchaseInfo
import lotto.model.WinningLotto
import lotto.view.InputView

class LottoController {

    private val purchaseInfo: PurchaseInfo
    private val winningLotto: WinningLotto

    init {
        purchaseInfo = retryWhileNoException { InputView.readPurchasePrice() }
        val winningLottoNumbers = retryWhileNoException { InputView.readWinningLottoNumbers() }
        winningLotto = retryWhileNoException {
            WinningLotto(winningLottoNumbers, InputView.readBonusNumber())
        }
    }

    fun run() {

    }

    private fun <T> retryWhileNoException(action: () -> T): T {
        return try {
            action()
        } catch (e: IllegalArgumentException) {
            println("${StringConstants.ERROR_MESSAGE} ${e.localizedMessage}")
            action()
        }
    }
}
