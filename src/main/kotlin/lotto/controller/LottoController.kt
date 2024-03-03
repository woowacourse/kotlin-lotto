package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoStore
import lotto.model.PurchaseAmount
import lotto.model.WinningLottoCalculator
import lotto.model.WinningRank
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

    private fun readWinningLotto(): WinningLottoCalculator {
        outputView.printWinningNumbersMessage()
        val winningLotto = Lotto.lottoNumbersOf(inputView.readWinningNumbers())

        outputView.printBonusNumberMessage()
        val bonusNumber = LottoNumber.from(inputView.readWinningBonusNumber())
        return WinningLottoCalculator(winningLotto, bonusNumber)
    }

    private fun showWinningResult(
        winningLottoCalculator: WinningLottoCalculator,
        purchaseAmount: Int,
    ) {
        val rankCounts = winningLottoCalculator.getWinningResult(lottoStore.lottos)

        outputView.printWinningStatisticsMessage()
        WinningRank.entries.forEach { rank ->
            if (rank != WinningRank.NONE) {
                outputView.printRankStatistics(rank, rankCounts[rank] ?: DEFAULT_COUNT)
            }
        }

        val profitRate = winningLottoCalculator.calculateProfitRate(purchaseAmount, rankCounts)
        outputView.printProfitRateMessage(profitRate)
    }

    companion object {
        const val PURCHASE_UNIT = 1000
        private const val DEFAULT_COUNT = 0
    }
}
