package lotto.controller

import lotto.model.LottoNumberGenerator
import lotto.model.LottoNumbers
import lotto.model.LottoStore
import lotto.model.Lottos
import lotto.model.PurchaseAmount
import lotto.model.WinningPrizeCalculator
import lotto.model.WinningRank
import lotto.view.InputView
import lotto.view.OutputView
import model.Lotto
import model.LottoNumber

class LottoController(private val inputView: InputView, private val outputView: OutputView) {
    fun run() {
        val purchaseAmount = initPurchaseAmount()
        val numberOfLottos = getNumberOfLotto(purchaseAmount)
        val lottoStore = LottoStore(numberOfLottos, LottoNumberGenerator())
        val lottos = initGenerateLottos(lottoStore)
        val (winningNumbers, bonusNumber) = generateWinningNumbers()
        showLottoResult(lottos, winningNumbers, bonusNumber, purchaseAmount.money)
    }

    private fun initPurchaseAmount(): PurchaseAmount {
        outputView.printPurchaseAmountMessage()
        return PurchaseAmount(inputView.readPurchaseAmount())
    }

    private fun getNumberOfLotto(purchaseAmount: PurchaseAmount): Int {
        val numberOfLottos = purchaseAmount.getNumberOfLottos()
        outputView.printNumberOfLottoMessage(numberOfLottos)
        return numberOfLottos
    }

    private fun initGenerateLottos(lottoStore: LottoStore): Lottos {
        val lottos = lottoStore.generateLottos()
        outputView.printLottoNumbers(lottos)
        return lottos
    }

    private fun generateWinningNumbers(): Pair<Lotto, LottoNumber> {
        outputView.printWinningNumbersMessage()
        val winningNumbers =
            Lotto(LottoNumbers(inputView.readWinningNumbers().map { LottoNumber(it.toInt()) }))
        outputView.printBonusNumberMessage()
        val bonusNumber = LottoNumber(inputView.readWinningBonusNumber())
        return Pair(winningNumbers, bonusNumber)
    }

    private fun showLottoResult(
        lottos: Lottos,
        winningNumbers: Lotto,
        bonusNumber: LottoNumber,
        purchaseAmount: Int,
    ) {
        val rankCounts = lottos.winningResult(winningNumbers, bonusNumber)
        outputView.printWinningMessage()
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
        private const val DEFAULT_COUNT = 0
    }
}
