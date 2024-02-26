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
    private val purchaseOrder: PurchaseOrder by lazy { readPurchasePrice() }
    private val winningLotto: WinningLotto by lazy { readWinningLotto() }

    private fun readPurchasePrice() = retryWhileNoException { InputView.readPurchasePrice() }

    private fun readWinningLotto(): WinningLotto {
        val winningLottoNumbers = retryWhileNoException { InputView.readWinningLottoNumbers() }
        return retryWhileNoException {
            WinningLotto(winningLottoNumbers, InputView.readBonusNumber())
        }
    }

    fun run() {
        val lottoStore = LottoStore.buyLottos(purchaseOrder, lottoNumberGenerator)
        OutputView.printPurchaseLotto(lottoStore)

        val winningStatistics = winningLotto.calculateWinningStatistics(lottoStore)
        OutputView.printWinningStatistics(winningStatistics)

        val profitRatio = winningStatistics.calculateProfitRatio(purchaseOrder)
        OutputView.printProfitRatio(profitRatio)
    }
}
