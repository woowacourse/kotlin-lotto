package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumberGenerator
import lotto.model.LottoStore
import lotto.model.ProfitRatio
import lotto.model.PurchaseOrder
import lotto.model.WinningLotto
import lotto.model.WinningStatistics
import lotto.utils.retryWhileNoException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val lottoNumberGenerator: LottoNumberGenerator,
) {
    fun run() {
        val purchaseOrder = readPurchaseOrder()
        val lottos = initializePurchaseLottos(purchaseOrder)
        val winningLotto = readWinningLotto()

        val winningStatistics = getWinningStatistics(lottos, winningLotto)
        getProfitRatio(winningStatistics, purchaseOrder)
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
        val automaticLottos = LottoStore.buyAutoMaticLottos(purchaseOrder, lottoNumberGenerator)
        return LottoStore.combineLottos(manualLottos, automaticLottos).also {
            OutputView.printPurchaseLotto(it)
        }
    }

    private fun readManualLottos(manualLottoSize: Int): List<Lotto> {
        return retryWhileNoException { InputView.readManualLottos(manualLottoSize) }
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
        return winningLotto.calculateWinningStatistics(lottos).also {
            OutputView.printWinningStatistics(it)
        }
    }

    private fun getProfitRatio(
        winningStatistics: WinningStatistics,
        purchaseOrder: PurchaseOrder,
    ): ProfitRatio {
        return winningStatistics.calculateProfitRatio(purchaseOrder.price).also {
            OutputView.printProfitRatio(it)
        }
    }
}
