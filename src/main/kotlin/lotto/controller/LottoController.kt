package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.ManualLottoMachine
import lotto.model.ProfitRatio
import lotto.model.PurchaseOrder
import lotto.model.WinningLotto
import lotto.model.WinningStatistics
import lotto.utils.retryWhileNoException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val automaticLottoMachine: LottoMachine,
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
        return retryWhileNoException {
            val inputManualLottos = readManualLottos(purchaseOrder.manualLottoSize)
            val manualLottos = ManualLottoMachine(inputManualLottos).generate(purchaseOrder.manualLottoSize)
            val automaticLottos = automaticLottoMachine.generate(purchaseOrder.automaticLottoSize)
            manualLottos + automaticLottos
        }
    }

    private fun readManualLottos(manualAmount: Int): List<List<Int>> {
        return retryWhileNoException { InputView.readManualLottos(manualAmount) }
    }

    private fun readWinningLotto(): WinningLotto {
        return retryWhileNoException {
            val winningLottoNumbers = InputView.readWinningLottoNumbers()
            val bonusLottoNumber = InputView.readBonusNumber()
            WinningLotto(Lotto.create(winningLottoNumbers), LottoNumber.from(bonusLottoNumber))
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
