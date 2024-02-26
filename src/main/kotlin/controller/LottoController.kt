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
import util.Constant
import view.InputView
import view.OutputView

class LottoController {
    private lateinit var buyer: Buyer
    private lateinit var winningStatistics: WinningStatistics

    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        buyer = Buyer(purchaseAmount)
        val numberOfManualLotto = InputView.inputPurchaseSizeOfManualLotto(purchaseAmount)
        publishLottos(numberOfManualLotto)
        val winningLotto = drawWinningLotto()
        winningStatistics = makeWinningStatics(buyer.purchasedLotto, winningLotto)
        displayWinningStatistics(purchaseAmount)
    }

    private fun generateLottoNumbers(): List<LottoNumber> {
        val numberGenerator = NumberGenerator()
        val randomNumbers = numberGenerator.makeRandomNumbers()

        return numberGenerator.drawSixNumbers(randomNumbers).map { LottoNumber(it) }
    }

    private fun publishLottos(numberOfManualLotto: Int) {
        if (numberOfManualLotto > 0) buyer.buyLottos(generateManualLottos(numberOfManualLotto))
        buyer.buyLottos(generateAutoLottos(numberOfManualLotto))

        displayPurchaseResult(numberOfManualLotto, buyer.numberOfLotto - numberOfManualLotto)
    }

    private fun generateManualLottos(numberOfManualLotto: Int): Lottos {
        InputView.inputGuideManualLottoNumbers()
        val manualLottos = Lottos(List(numberOfManualLotto) { Lotto(InputView.inputManualLottos()) })
        return manualLottos
    }

    private fun generateAutoLottos(numberOfManualLotto: Int): Lottos {
        val autoLottos = Lottos(List(buyer.numberOfLotto - numberOfManualLotto) { Lotto(generateLottoNumbers()) })
        return autoLottos
    }

    private fun displayPurchaseResult(
        numberOfManualLotto: Int,
        numberOfAutoLotto: Int,
    ) {
        OutputView.outputNumberOfLotto(numberOfManualLotto, numberOfAutoLotto)
        OutputView.outputLottos(buyer.purchasedLotto)
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
            MutableList(Constant.LOTTO_SIZE) {
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

    private fun displayWinningStatistics(purchaseAmount: Int) {
        OutputView.outputWinningStatistics(winningStatistics)
        displayRateOfReturn(purchaseAmount)
    }

    private fun displayRateOfReturn(purchaseAmount: Int) {
        val rateOfReturn = winningStatistics.calculateRateOfReturn(purchaseAmount)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}
