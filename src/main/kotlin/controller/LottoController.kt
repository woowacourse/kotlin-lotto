package lotto.controller

import lotto.model.LottoNumberGenerator
import lotto.model.LottoNumbers
import lotto.model.LottoStore
import lotto.model.Lottos
import lotto.model.PurchaseAmount
import lotto.model.WinningNumbers
import lotto.model.WinningPrizeCalculator
import lotto.model.WinningRank
import lotto.view.InputView
import lotto.view.OutputView
import model.Lotto
import model.LottoNumber

class LottoController(private val inputView: InputView, private val outputView: OutputView) {
    fun run() {
        val purchaseAmount = initPurchaseAmount()
        val lottos = initGenerateLottos(purchaseAmount)
        val winningNumbers = generateWinningNumbers()
        showLottoResult(lottos, winningNumbers, purchaseAmount.money)
    }

    private fun initPurchaseAmount(): PurchaseAmount {
        outputView.printPurchaseAmountMessage()
        val purchaseAmount = inputView.readPurchaseAmount()
        outputView.printNumberOfManualLottosMessage()
        val numberOfManualLottos = inputView.readNumberOfManualLottos()
        return PurchaseAmount(purchaseAmount, numberOfManualLottos)
    }

    private fun initGenerateLottos(purchaseAmount: PurchaseAmount): Lottos {
        val numberOfAutoLottos = purchaseAmount.getNumberOfAutoLottos()
        val numberOfManualLottos = purchaseAmount.numberOfManualLottos
        val autoLottos = LottoStore.generateAutoLottos(numberOfAutoLottos, LottoNumberGenerator())
        outputView.printEnterManualLottoNumberMessage()
        val manualLottos =
            inputView.readManualLottoNumber(numberOfManualLottos)
                .map { Lotto(LottoNumbers(it.map { LottoNumber.from(it.toInt()) }.sortedBy { it.number })) }
        val manualLottoBundle = LottoStore.generateManualLottos(manualLottos)
        val lottoBundle = Lottos(autoLottos.lottos + manualLottoBundle.lottos)
        outputView.printNumberOfLottoMessage(numberOfManualLottos, numberOfAutoLottos)
        outputView.printLottoNumbers(lottoBundle)
        return lottoBundle
    }

    private fun generateWinningNumbers(): WinningNumbers {
        outputView.printWinningNumbersMessage()
        val winningLotto = inputView.readWinningNumbers()
        outputView.printBonusNumberMessage()
        val bonusNumber = inputView.readWinningBonusNumber()
        return WinningNumbers.of(winningLotto, bonusNumber)
    }

    private fun showLottoResult(
        lottos: Lottos,
        winningNumbers: WinningNumbers,
        purchaseAmount: Int,
    ) {
        val rankCounts = lottos.winningResult(winningNumbers)
        outputView.printWinningMessage()
        WinningRank.entries.forEach { rank ->
            if (rank != WinningRank.NONE) {
                outputView.printRankStatistics(rank, rankCounts[rank] ?: DEFAULT_COUNT)
            }
        }
        val profitRate = WinningPrizeCalculator.calculateProfitRate(purchaseAmount, rankCounts)
        outputView.printProfitRateMessage(profitRate)
    }

    companion object {
        private const val DEFAULT_COUNT = 0
    }
}
