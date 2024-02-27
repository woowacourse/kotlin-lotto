package lotto.controller

import lotto.constants.GameConstant.DEFAULT_COUNT
import lotto.constants.GameConstant.PURCHASE_UNIT
import lotto.model.LottoNumberGenerator
import lotto.model.LottoStore
import lotto.model.PurchaseAmount
import lotto.model.WinningLotto
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
        val winningNumbers = readWinningNumbers()
        showLottoResult(lottoStore, winningNumbers, purchaseAmount)
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
}
