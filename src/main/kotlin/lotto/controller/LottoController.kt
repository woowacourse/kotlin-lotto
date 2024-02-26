package lotto.controller

import lotto.model.LottoStore
import lotto.model.PurchaseInfo
import lotto.model.WinningLotto
import lotto.service.RandomLottoNumberGenerator
import lotto.service.ResultCalculator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val purchaseInfo: PurchaseInfo by lazy { readPurchasePrice() }
    private val winningLotto: WinningLotto by lazy { readWinningLotto() }

    fun run() {
        val lottoStore = LottoStore(purchaseInfo, RandomLottoNumberGenerator())
        OutputView.printPurchaseLotto(lottoStore)

        val prizeCount = ResultCalculator.calculatePrizeCount(lottoStore, winningLotto)
        val profitRatio = ResultCalculator.calculateProfitRatio(purchaseInfo, prizeCount)
        OutputView.printWinningStatistics(prizeCount, profitRatio)
    }

    private fun readPurchasePrice() = InputView.readPurchasePrice()

    private fun readWinningLotto(): WinningLotto {
        val winningLottoNumbers = InputView.readWinningLottoNumbers()
        return WinningLotto(winningLottoNumbers, InputView.readBonusNumber())
    }
}
