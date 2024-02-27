package lotto.controller

import lotto.model.LottoNumberGenerator
import lotto.model.LottoStore
import lotto.model.PurchaseOrder
import lotto.model.WinningLotto
import lotto.utils.retryWhileNoException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val lottoNumberGenerator: LottoNumberGenerator,
) {
    fun run() {
        val purchasePrice = readPurchasePrice()
        val manualLottoSize = readManualLottoSize()
        val purchaseOrder = PurchaseOrder(purchasePrice, manualLottoSize)

        val lottoStore = LottoStore.buyLottos(listOf(), purchaseOrder, lottoNumberGenerator)
        OutputView.printPurchaseLotto(lottoStore)

        val winningLotto = readWinningLotto()
        val winningStatistics = winningLotto.calculateWinningStatistics(lottoStore)
        OutputView.printWinningStatistics(winningStatistics)

        val profitRatio = winningStatistics.calculateProfitRatio(purchaseOrder.price)
        OutputView.printProfitRatio(profitRatio)
    }

    private fun readPurchasePrice() = retryWhileNoException { InputView.readPurchasePrice() }

    private fun readManualLottoSize() = retryWhileNoException { InputView.readManualLottoSize() }

    private fun readWinningLotto(): WinningLotto {
        val winningLottoNumbers = retryWhileNoException { InputView.readWinningLottoNumbers() }
        return retryWhileNoException {
            WinningLotto(winningLottoNumbers, InputView.readBonusNumber())
        }
    }
}
