package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoStore
import lotto.model.ProfitRatio
import lotto.model.PurchaseOrder
import lotto.model.WinningLotto
import lotto.model.WinningStatistics
import lotto.utils.retryWhileNoException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val lottoMachine: LottoMachine,
) {
    fun run() {
        val purchaseOrder = readPurchaseOrder()
        val lottos = initializePurchaseLottos(purchaseOrder)
        OutputView.printPurchaseLotto(purchaseOrder, lottos)
        val winningLotto = readWinningLotto()

        val winningStatistics = getWinningStatistics(lottos, winningLotto)
        OutputView.printWinningStatistics(winningStatistics)
        val profitRatio = getProfitRatio(winningStatistics, purchaseOrder)
        OutputView.printProfitRatio(profitRatio)
    }

    private fun readPurchaseOrder(): PurchaseOrder {
        return retryWhileNoException {
            val purchasePrice = InputView.readPurchasePrice()
            val manualLottoSize = InputView.readManualLottoSize()
            PurchaseOrder(purchasePrice, manualLottoSize)
        }
    }

    private fun initializePurchaseLottos(purchaseOrder: PurchaseOrder): List<Lotto> {
        val manualLottos = readManualLottos(purchaseOrder.manualLottoSize)
        val automaticLottos = LottoStore.buyAutoMaticLottos(purchaseOrder.automaticLottoSize, lottoMachine)
        return manualLottos + automaticLottos
    }

    private fun readManualLottos(manualAmount: Int): List<Lotto> {
        return retryWhileNoException { InputView.readManualLottos(manualAmount) }
    }

    private fun readWinningLotto(): WinningLotto {
        return retryWhileNoException {
            val winningLottoNumbers = InputView.readWinningLottoNumbers()
            WinningLotto(winningLottoNumbers, InputView.readBonusNumber())
        }
    }

    private fun getWinningStatistics(
        lottos: List<Lotto>,
        winningLotto: WinningLotto,
    ): WinningStatistics {
        return winningLotto.calculateWinningStatistics(lottos)
    }

    private fun getProfitRatio(
        winningStatistics: WinningStatistics,
        purchaseOrder: PurchaseOrder,
    ): ProfitRatio {
        return winningStatistics.calculateProfitRatio(purchaseOrder.price)
    }
}
