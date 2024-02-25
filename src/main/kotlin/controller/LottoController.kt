package controller

import model.Buyer
import model.Lotto
import model.LottoGenerator
import model.Lottos
import model.Rank
import model.WinningLotto
import model.WinningStatistic
import model.WinningStatistics
import view.InputView
import view.OutputView

class LottoController {
    private lateinit var buyer: Buyer
    private lateinit var winningStatistics: WinningStatistics

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
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun makeWinningStatics(
        lottos: Lottos,
        winningLotto: WinningLotto,
    ): WinningStatistics {
        val results =
            MutableList(6) {
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
