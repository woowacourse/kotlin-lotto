package lotto.controller

import lotto.model.Lotto
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
    fun run() {
        val purchaseAmount = initPurchaseAmount()
        val lottoStore = generateLottoStore(purchaseAmount.numberOfLottos)
        val winningNumbers = readWinningNumbers()
        showLottoResult(lottoStore, winningNumbers, purchaseAmount.lottoPurchaseAmount)
    }

    private fun initPurchaseAmount(): PurchaseAmount {
        outputView.printPurchaseAmountMessage()
        return PurchaseAmount(inputView.readPurchaseAmount(), PURCHASE_UNIT)
    }

    private fun generateLottoStore(numberOfLottos: Int): LottoStore {
        outputView.printNumberOfLottoMessage(numberOfLottos)
        val lottoStore = LottoStore(numberOfLottos, LottoNumberGenerator())
        outputView.printLottoNumbers(lottoStore.lottos)
        return lottoStore
    }

    private fun readWinningNumbers(): WinningLotto {
        outputView.printWinningNumbersMessage()
        val winningLotto = Lotto.lottoNumbersOf(inputView.readWinningNumbers())

        outputView.printBonusNumberMessage()
        val bonusNumber = LottoNumber(inputView.readWinningBonusNumber())
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
