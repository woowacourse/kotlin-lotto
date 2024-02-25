package controller

import model.Lotto
import model.LottoGenerator
import model.LottoPrize
import model.Lottos
import model.WinningLotto
import model.WinningStatistic
import model.WinningStatistics
import util.Constant
import view.InputView
import view.OutputView

class LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val lottos = publishLottos(purchaseAmount)
        val winningLotto = drawWinningLotto()
        val winningStatistics = analyzeWinningStatics(lottos, winningLotto)

        displayPurchaseResults(winningStatistics, purchaseAmount)
    }

    private fun publishLottos(purchaseAmount: Int): Lottos {
        val numberOfLotto = calculateNumberOfLotto(purchaseAmount)
        val lottos = Lottos(List(numberOfLotto) { LottoGenerator.generateLotto() })
        displayPurchaseResult(lottos)

        return lottos
    }

    private fun calculateNumberOfLotto(purchaseAmount: Int): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }

    private fun displayPurchaseResult(lottos: Lottos) {
        OutputView.outputNumberOfLotto(lottos.publishedLottos.size)
        OutputView.outputLottos(lottos)
    }

    private fun drawWinningLotto(): WinningLotto {
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)

        return WinningLotto(Lotto(winningNumbers), bonusNumber)
    }

    private fun analyzeWinningStatics(
        lottos: Lottos,
        winningLotto: WinningLotto,
    ): WinningStatistics {
        val results =
            MutableList(6) {
                WinningStatistic(Pair(LottoPrize.getLottoPrizeByOrdinal(it), 0))
            }
        repeat(lottos.publishedLottos.size) { index ->
            val lottoPrize = judgeLottoPrize(lottos.publishedLottos[index], winningLotto)
            val currentCount = results[lottoPrize.ordinal].result.second
            results[lottoPrize.ordinal] = WinningStatistic(Pair(lottoPrize, currentCount + 1))
        }

        return WinningStatistics(results)
    }

    private fun judgeLottoPrize(
        lotto: Lotto,
        winningLotto: WinningLotto,
    ): LottoPrize {
        val countOfMatch = winningLotto.calculateCountOfMatch(lotto)
        val bonusMatched = winningLotto.checkBonusNumberMatched(lotto)

        return LottoPrize.getLottoPrize(countOfMatch, bonusMatched)
    }

    private fun displayPurchaseResults(
        winningStatistics: WinningStatistics,
        purchaseAmount: Int,
    ) {
        OutputView.outputWinningStatistics(winningStatistics)

        val rateOfReturn = winningStatistics.calculateRateOfReturn(purchaseAmount)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}
