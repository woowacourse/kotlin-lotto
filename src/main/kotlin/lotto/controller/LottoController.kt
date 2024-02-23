package lotto.controller

import lotto.model.LottoStore
import lotto.model.PurchaseInfo
import lotto.model.WinningLotto
import lotto.service.RandomLottoNumberGenerator
import lotto.service.ResultCalculator
import lotto.utils.retryWhileNoException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    private val purchaseInfo: PurchaseInfo by lazy { readPurchasePrice() }
    private val winningLotto: WinningLotto by lazy { readWinningLotto() }

    fun run() {
        val lottoStore = LottoStore.create(purchaseInfo, RandomLottoNumberGenerator)
        OutputView.printPurchaseLotto(lottoStore)

        val prizeCount = lottoStore.calculatePrizeCount(winningLotto)
        val profitRatio = ResultCalculator.calculateProfitRatio(purchaseInfo, prizeCount)
        OutputView.printWinningStatistics(prizeCount, profitRatio)
    }

    private fun readPurchasePrice() = retryWhileNoException { InputView.readPurchasePrice() }

    private fun readWinningLotto(): WinningLotto {
        val winningLottoNumbers = retryWhileNoException { InputView.readWinningLottoNumbers() }
        return retryWhileNoException {
            WinningLotto(winningLottoNumbers, InputView.readBonusNumber())
        }
    }
}
