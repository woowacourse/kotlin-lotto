package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoCalculator
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoPurchaseAmount
import lotto.domain.WinningLotto
import lotto.util.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val lottos = getAndPrintPurchasedLottos(purchaseAmount)
        val lottoCalculator = getWinningInfoAndCalculator()
        val winningStats = getAndPrintWinningStats(lottoCalculator, lottos)
        val prize = lottoCalculator.calculatePrize(winningStats)
        outputView.printProfit(lottoCalculator.calculateProfit(prize, purchaseAmount.money))
    }

    private fun getWinningInfoAndCalculator(): LottoCalculator {
        val winningNumber = getWinningNumber()
        val winningLotto = getWinningLotto(winningNumber)
        return LottoCalculator(winningLotto.winningNumber, winningLotto.bonusNumber)
    }

    private fun getAndPrintPurchasedLottos(purchaseAmount: LottoPurchaseAmount): List<Lotto> {
        val lottos = LottoMachine().buyLottos(purchaseAmount.money)
        outputView.printPurchasedLottos(lottos)
        return lottos
    }

    private fun getAndPrintWinningStats(
        lottoCalculator: LottoCalculator,
        lottos: List<Lotto>,
    ): Map<Rank, Int> {
        val winningStats = lottoCalculator.matchLottos(lottos)
        printWinningStats(winningStats)
        return winningStats
    }

    private fun printWinningStats(winningStats: Map<Rank, Int>) {
        outputView.printWinningStats()
        for ((state, count) in winningStats) {
            printWinningStatsByRank(state, count)
        }
    }

    private fun printWinningStatsByRank(
        state: Rank,
        count: Int,
    ) {
        if (state == Rank.SECOND) {
            outputView.printWinningStatWithBonusBall(state, count)
        } else if (state != Rank.NONE) {
            outputView.printWinningStatWIthNoBonusBall(state, count)
        }
    }

    private fun getPurchaseAmount(): LottoPurchaseAmount {
        val purchaseAmount = inputView.getPurchaseAmount()
        return LottoPurchaseAmount(purchaseAmount.toInt())
    }

    private fun getWinningNumber(): Lotto {
        val winningNumber = inputView.getWinningNumber().split(DELIMITERS).map { it.trim() }
        return Lotto(winningNumber.map { LottoNumber(it.toInt()) })
    }

    private fun getWinningLotto(winningNumber: Lotto): WinningLotto {
        val bonusNumber = inputView.getBonusNumber()
        return WinningLotto(winningNumber, LottoNumber(bonusNumber.toInt()))
    }

    companion object {
        private const val DELIMITERS = ","
    }
}
