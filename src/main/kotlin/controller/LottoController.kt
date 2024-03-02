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
        val (numberOfManualLottos, numberOfAutoLottos) = getNumberOfLotto(purchaseAmount)
        val lottos = initGenerateLottos(numberOfManualLottos, numberOfAutoLottos)
        val (winningNumbers, bonusNumber) = generateWinningNumbers()
        showLottoResult(lottos, winningNumbers, bonusNumber, purchaseAmount.money)
    }

    private fun initPurchaseAmount(): PurchaseAmount {
        outputView.printPurchaseAmountMessage()
        val purchaseAmount = inputView.readPurchaseAmount()
        outputView.printNumberOfManualLottosMessage()
        val numberOfManualLottos = inputView.readNumberOfManualLottos()
        return PurchaseAmount(purchaseAmount, numberOfManualLottos)
    }

    private fun getNumberOfLotto(purchaseAmount: PurchaseAmount): Pair<Int, Int> {
        val numberOfAutoLottos = purchaseAmount.getNumberOfAutoLottos()
        val numberOfManualLottos = purchaseAmount.numberOfManualLottos
        return Pair(numberOfManualLottos, numberOfAutoLottos)
    }

    private fun initGenerateLottos(
        numberOfManualLottos: Int,
        numberOfAutoLottos: Int,
    ): Lottos {
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

    private fun generateWinningNumbers(): Pair<Lotto, LottoNumber> {
        outputView.printWinningNumbersMessage()
        val winningNumbers =
            Lotto(LottoNumbers(inputView.readWinningNumbers().map { LottoNumber(it) }))
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
        val profitRate = WinningPrizeCalculator.calculateProfitRate(purchaseAmount, rankCounts)
        outputView.printProfitRateMessage(profitRate)
    }

    companion object {
        private const val DEFAULT_COUNT = 0
    }
}
