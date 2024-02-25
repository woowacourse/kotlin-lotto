package controller

import model.Buyer
import model.Lotto
import model.LottoGenerator
import model.LottoPrize
import model.Lottos
import model.WinningLotto
import model.WinningStatistic
import model.WinningStatistics
import view.InputView
import view.OutputView

class LottoController {
    private lateinit var winningStatistics: WinningStatistics
    private lateinit var buyer: Buyer

    fun run() {
        val purchaseAmount = InputView.inputPurchaseAmount()
        buyer = Buyer(purchaseAmount)
        val lottos = publishLottos()
        val winningLotto = drawWinningLotto()
        winningStatistics = makeWinningStatics(lottos, winningLotto)
        displayWinningStatistics(purchaseAmount)
    }

    private fun publishLottos(): Lottos {
        val lottos = Lottos(List(buyer.numberOfLotto) { LottoGenerator.generateLotto() })
        buyer.buyLottos(lottos)
        displayPurchaseResult()

        return lottos
    }

    private fun displayPurchaseResult() {
        OutputView.outputNumberOfLotto(buyer.numberOfLotto)
        OutputView.outputLottos(buyer.lottos)
    }

    private fun drawWinningLotto(): WinningLotto {
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)

        return WinningLotto(Lotto(winningNumbers), bonusNumber)
    }

    private fun makeWinningStatics(
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

    private fun displayWinningStatistics(purchaseAmount: Int) {
        OutputView.outputWinningStatistics(winningStatistics)
        displayRateOfReturn(purchaseAmount)
    }

    private fun displayRateOfReturn(purchaseAmount: Int) {
        val rateOfReturn = winningStatistics.calculateRateOfReturn(purchaseAmount)
        OutputView.outputRateOfReturn(rateOfReturn)
    }
}
