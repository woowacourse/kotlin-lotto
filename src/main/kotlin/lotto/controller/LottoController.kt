package lotto.controller

import lotto.model.Lotto
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
        val purchaseOrder = readPurchaseOrder()
        val manualLottos = readManualLottos(purchaseOrder.manualLottoSize)
        val automaticLottos = LottoStore.buyAutoMaticLottos(purchaseOrder, lottoNumberGenerator)
        val lottos = LottoStore.combineLottos(manualLottos, automaticLottos)
        OutputView.printPurchaseLotto(lottos)

        val winningLotto = readWinningLotto()
        val winningStatistics = winningLotto.calculateWinningStatistics(lottos)
        OutputView.printWinningStatistics(winningStatistics)

        val profitRatio = winningStatistics.calculateProfitRatio(purchaseOrder.price)
        OutputView.printProfitRatio(profitRatio)
    }

    private fun readPurchaseOrder() =
        retryWhileNoException {
            val purchasePrice = InputView.readPurchasePrice()
            val manualLottoSize = InputView.readManualLottoSize()
            PurchaseOrder(purchasePrice, manualLottoSize)
        }

    private fun readManualLottos(manualLottoSize: Int): List<Lotto> {
        return retryWhileNoException { InputView.readManualLottos(manualLottoSize) }
    }

    private fun readWinningLotto() =
        retryWhileNoException {
            val winningLottoNumbers = InputView.readWinningLottoNumbers()
            WinningLotto(winningLottoNumbers, InputView.readBonusNumber())
        }
}
