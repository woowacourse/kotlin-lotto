package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoStore
import lotto.model.PurchaseAmount
import lotto.model.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val inputView: InputView, private val outputView: OutputView) {
    private val lottoStore = LottoStore()

    fun run() {
        val purchaseAmount = initPurchaseAmount()
        val lottoCount = initManualLottoCount(purchaseAmount.purchasableLottoCount)
        generateLottos(lottoCount)
        showLottos(lottoCount)
        val winningLotto = readWinningLotto()
        showWinningResult(winningLotto, purchaseAmount.lottoPurchaseAmount)
    }

    private fun initPurchaseAmount(): PurchaseAmount {
        outputView.printPurchaseAmountMessage()
        return PurchaseAmount(inputView.readPurchaseAmount(), PURCHASE_UNIT)
    }

    private fun initManualLottoCount(purchasableLottoCount: Int): LottoCount {
        outputView.printManualLottoCountMessage()
        val newManualLottoCount = inputView.readManualLottoCount()
        return LottoCount(purchasableLottoCount, newManualLottoCount)
    }

    private fun generateLottos(lottoCount: LottoCount) {
        outputView.printManualLottoNumbersMessage()
        val newManualLottoNumbers = inputView.readManualLottoNumbers(lottoCount.manualLottoCount)
        lottoStore.generateLottos(newManualLottoNumbers, lottoCount.autoLottoCount)
    }

    private fun showLottos(lottoCount: LottoCount) {
        outputView.printTotalLottoCountMessage(lottoCount.manualLottoCount, lottoCount.autoLottoCount)
        outputView.printLottoNumbers(lottoStore)
    }

    private fun readWinningLotto(): WinningResult {
        outputView.printWinningNumbersMessage()
        val winningLotto = Lotto.lottoNumbersOf(inputView.readWinningNumbers())

        outputView.printBonusNumberMessage()
        val bonusNumber = LottoNumber.from(inputView.readWinningBonusNumber())
        return WinningResult(winningLotto, bonusNumber)
    }

    private fun showWinningResult(
        winningResult: WinningResult,
        purchaseAmount: Int,
    ) {
        val rankCounts = winningResult.getWinningResult(lottoStore.lottos)
        outputView.printWinningResult(rankCounts)
        val profitRate = winningResult.calculateProfitRate(purchaseAmount, rankCounts)
        outputView.printProfitRateMessage(profitRate)
    }

    companion object {
        const val PURCHASE_UNIT = 1000
    }
}
