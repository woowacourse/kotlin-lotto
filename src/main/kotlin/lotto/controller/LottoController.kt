package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoNumberGenerator
import lotto.model.LottoStore
import lotto.model.PurchaseAmount
import lotto.model.WinningLotto
import lotto.model.WinningPrizeCalculator
import lotto.model.WinningRank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val inputView: InputView, private val outputView: OutputView) {
    private val lottoStore = LottoStore(LottoNumberGenerator())

    fun run() {
        val purchaseAmount = initPurchaseAmount()
        val lottoCount = initManualLottoCount(purchaseAmount.purchasableLottoCount)
        generateLottos(lottoCount)
        showLottos(lottoCount)
        val winningLotto = readWinningLotto()
        showLottoResult(lottoStore, winningLotto, purchaseAmount.lottoPurchaseAmount)
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
        lottoStore.generateManualLottos(newManualLottoNumbers)
        lottoStore.generateAutoLottos(lottoCount.autoLottoCount)
    }

    private fun showLottos(lottoCount: LottoCount) {
        outputView.printTotalLottoCountMessage(lottoCount.manualLottoCount, lottoCount.autoLottoCount)
        outputView.printLottoNumbers(lottoStore.lottos)
    }

    private fun readWinningLotto(): WinningLotto {
        outputView.printWinningNumbersMessage()
        val winningLotto = Lotto.lottoNumbersOf(inputView.readWinningNumbers())

        outputView.printBonusNumberMessage()
        val bonusNumber = LottoNumber.from(inputView.readWinningBonusNumber())
        return WinningLotto(winningLotto, bonusNumber)
    }

    private fun showLottoResult(
        lottoStore: LottoStore,
        winningLotto: WinningLotto,
        purchaseAmount: Int,
    ) {
        val rankCounts = lottoStore.getWinningResult(winningLotto)

        WinningRank.entries.forEach { rank ->
            if (rank != WinningRank.NONE) {
                outputView.printRankStatistics(rank, rankCounts[rank] ?: DEFAULT_COUNT)
            }
        }
        val profitAmount = WinningPrizeCalculator.calculateProfitAmount(rankCounts)
        val profitRate = WinningPrizeCalculator.calculateProfitRate(purchaseAmount, profitAmount)
        outputView.printProfitRateMessage(profitRate)
    }

    companion object {
        const val PURCHASE_UNIT = 1000
        private const val DEFAULT_COUNT = 0
    }
}
