package lotto.controller

import lotto.constants.GameConstant.DEFAULT_COUNT
import lotto.constants.GameConstant.PURCHASE_UNIT
import lotto.model.LottoNumberGenerator
import lotto.model.LottoStore
import lotto.model.PurchaseAmount
import lotto.model.WinningNumbersGenerator
import lotto.model.WinningPrizeCalculator
import lotto.model.WinningRank
import lotto.view.InputView
import lotto.view.OutputView
import model.Lotto
import model.LottoNumber

class LottoController(private val inputView: InputView, private val outputView: OutputView) {
    fun run() {
        val purchaseAmount = initPurchaseAmount()
        val numberOfLottos = initBuyLotto(purchaseAmount)
        val lottoStore = generateLotto(numberOfLottos)
        val (winningNumbers, bonusNumber) = generateWinningNumbers()
        showLottoResult(lottoStore, winningNumbers, bonusNumber, purchaseAmount)
    }

    private fun initPurchaseAmount(): Int {
        outputView.printPurchaseAmountMessage()
        return PurchaseAmount().getAmount(inputView.readPurchaseAmount())
    }

    private fun initBuyLotto(purchaseAmount: Int): Int {
        val numberOfLottos = purchaseAmount / PURCHASE_UNIT
        outputView.printNumberOfLottoMessage(numberOfLottos)
        return numberOfLottos
    }

    private fun generateLotto(numberOfLottos: Int): LottoStore {
        val lottoStore = LottoStore(numberOfLottos, LottoNumberGenerator())
        outputView.printLottoNumbers(lottoStore.lottos)
        return lottoStore
    }

    private fun generateWinningNumbers(): Pair<Lotto, LottoNumber> {
        outputView.printWinningNumbersMessage()
        val input = inputView.readWinningNumbers()
        val winningNumbersGenerator = WinningNumbersGenerator()
        val winningLotto = winningNumbersGenerator.generateWinningNumbers(input)

        outputView.printBonusNumberMessage()
        val bonusNumber = LottoNumber(inputView.readWinningBonusNumber())
        return Pair(winningLotto, bonusNumber)
    }

    private fun showLottoResult(
        lottoStore: LottoStore,
        winningNumbers: Lotto,
        bonusNumber: LottoNumber,
        purchaseAmount: Int,
    ) {
        val rankCounts = lottoStore.getWinningResult(winningNumbers, bonusNumber)

        WinningRank.entries.forEach { rank ->
            if (rank != WinningRank.NONE) {
                outputView.printRankStatistics(rank, rankCounts[rank] ?: DEFAULT_COUNT)
            }
        }
        val profitAmount = WinningPrizeCalculator.calculateProfitAmount(rankCounts)
        val profitRate = WinningPrizeCalculator.calculateProfitRate(purchaseAmount, profitAmount)
        outputView.printProfitRateMessage(profitRate)
    }
}
