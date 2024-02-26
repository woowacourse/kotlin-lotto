package controller

import model.Lotto
import model.LottoPrize
import model.Lottos
import model.WinningLotto
import model.WinningStatistic
import model.WinningStatistics
import view.InputView
import view.OutputView

class LottoController {
    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        val handpickedNumber = InputView.inputHandpickedNumber()
        val handpickedNumbers = InputView.inputHandpickedNumbers()
        val lottos = Lottos(purchaseAmount)
        displayPurchaseResult(lottos)

        val winningLotto = drawWinningLotto()
        val winningStatistics = analyzeWinningStatics(lottos, winningLotto)
        displayWinningStatistics(winningStatistics, purchaseAmount)
    }

    private fun displayPurchaseResult(lottos: Lottos) {
        OutputView.outputNumberOfLotto(lottos.getLottos().size)
        OutputView.outputLottos(lottos)
    }

    private fun drawWinningLotto(): WinningLotto {
        val lotto = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(lotto)

        return WinningLotto(Lotto(lotto), bonusNumber)
    }

    private fun analyzeWinningStatics(
        lottos: Lottos,
        winningLotto: WinningLotto,
    ): WinningStatistics {
        val results =
            MutableList(6) {
                WinningStatistic(Pair(LottoPrize.getLottoPrizeByOrdinal(it), 0))
            }
        repeat(lottos.getLottos().size) { index ->
            val lottoPrize = judgeLottoPrize(lottos.getLottos()[index], winningLotto)
            val currentCount = results[lottoPrize.ordinal].getWinningStatistic().second
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

    private fun displayWinningStatistics(
        winningStatistics: WinningStatistics,
        purchaseAmount: Int,
    ) {
        OutputView.outputWinningStatistics(winningStatistics)

        val rateOfReturn = winningStatistics.calculateRateOfReturn(purchaseAmount)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}
