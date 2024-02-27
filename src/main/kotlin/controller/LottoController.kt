package controller

import model.Buyer
import model.Lotto
import model.LottoNumber
import model.Lottos
import model.NumberGenerator
import model.Rank
import model.WinningLotto
import model.WinningStatistic
import model.WinningStatistics
import util.LottoConstants
import view.InputView
import view.OutputView

class LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val buyer = Buyer(purchaseAmount)
        val numberOfManualLotto = InputView.inputPurchaseSizeOfManualLotto(purchaseAmount)
        val userLotto = publishLottos(numberOfManualLotto, buyer)
        val winningLotto = drawWinningLotto()
        val winningStatistics = makeWinningStatics(Lottos(userLotto), winningLotto)
        displayWinningStatistics(purchaseAmount, winningStatistics)
    }

    private fun generateLottoNumbers(): List<LottoNumber> {
        val numberGenerator = NumberGenerator()
        val randomNumbers = numberGenerator.makeRandomNumbers()

        return numberGenerator.drawSixNumbers(randomNumbers).map { LottoNumber(it) }
    }

    private fun publishLottos(
        numberOfManualLotto: Int,
        buyer: Buyer,
    ): List<Lotto> {
        val manualLottos = generateManualLottos(numberOfManualLotto)
        val autoLottos = generateAutoLottos(buyer.numberOfLotto - numberOfManualLotto)
        displayPurchaseResult(manualLottos, autoLottos)

        return autoLottos.publishedLottos + manualLottos.publishedLottos
    }

    private fun generateManualLottos(numberOfManualLotto: Int): Lottos {
        if (numberOfManualLotto > LottoConstants.MINIMUM_PURCHASE_SIZE_OF_MANUAL_LOTTO) {
            InputView.inputGuideManualLottoNumbers()
        }
        val manualLottos = Lottos(List(numberOfManualLotto) { Lotto(InputView.inputManualLottos()) })
        return manualLottos
    }

    private fun generateAutoLottos(autoLottoCount: Int): Lottos {
        val autoLottos = Lottos(List(autoLottoCount) { Lotto(generateLottoNumbers()) })
        return autoLottos
    }

    private fun displayPurchaseResult(
        manualLotto: Lottos,
        autoLotto: Lottos,
    ) {
        OutputView.outputNumberOfLotto(manualLotto.publishedLottos.size, autoLotto.publishedLottos.size)
        OutputView.outputLottos(manualLotto)
        OutputView.outputLottos(autoLotto)
    }

    private fun drawWinningLotto(): WinningLotto {
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun makeWinningStatics(
        lottos: Lottos,
        winningLotto: WinningLotto,
    ): WinningStatistics {
        val results =
            MutableList(LottoConstants.SIZE) {
                WinningStatistic(Pair(Rank.getRankByOrdinal(it), 0))
            }
        repeat(lottos.publishedLottos.size) { index ->
            val rank = judgeRank(lottos.publishedLottos[index], winningLotto)
            val currentCount = results[rank.ordinal].result.second
            results[rank.ordinal] = WinningStatistic(Pair(rank, currentCount + 1))
        }

        return WinningStatistics(results)
    }

    private fun judgeRank(
        lotto: Lotto,
        winningLotto: WinningLotto,
    ): Rank {
        val countOfMatch = winningLotto.calculateCountOfMatch(lotto)
        val bonusMatched = winningLotto.checkBonusNumberMatched(lotto)
        return Rank.getRank(countOfMatch, bonusMatched)
    }

    private fun displayWinningStatistics(
        purchaseAmount: Int,
        winningStatistics: WinningStatistics,
    ) {
        OutputView.outputWinningStatistics(winningStatistics)
        displayRateOfReturn(purchaseAmount, winningStatistics)
    }

    private fun displayRateOfReturn(
        purchaseAmount: Int,
        winningStatistics: WinningStatistics,
    ) {
        val rateOfReturn = winningStatistics.calculateRateOfReturn(purchaseAmount)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}
