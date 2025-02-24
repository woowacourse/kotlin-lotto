package lotto.controller

import lotto.domain.*
import lotto.util.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val lottoMachine = LottoMachine()
    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val manualLottoCount = getManualLottoCount(purchaseAmount.purchasableCount)
        val lottos = getAndPrintPurchasedLottos(purchaseAmount, manualLottoCount.lottoCount)
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

    private fun getAndPrintPurchasedLottos(purchaseAmount: LottoPurchaseAmount, manualLottoCount: Int): List<Lotto> {
        val lottos = lottoMachine.buyLottos(purchaseAmount.purchasableCount - manualLottoCount)
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
        return retryInput {
            LottoPurchaseAmount(inputView.getPurchaseAmount().toInt())
        }
    }

    private fun getManualLottoCount(purchasableCount: Int): ManualLottoCount {
        return retryInput {
            ManualLottoCount(inputView.getManualLottoCount().toInt(), purchasableCount)
        }
    }

    private fun getWinningNumber(): Lotto {
        return retryInput {
            val winningNumber = inputView.getWinningNumber().split(DELIMITERS).map { it.trim() }
            Lotto(winningNumber.map { LottoNumber(it.toInt()) })
        }
    }

    private fun getWinningLotto(winningNumber: Lotto): WinningLotto {
        return retryInput {
            val bonusNumber = inputView.getBonusNumber()
            WinningLotto(winningNumber, LottoNumber(bonusNumber.toInt()))
        }
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse {
                retryInput(inputFunction)
            }
    }

    companion object {
        private const val DELIMITERS = ","
    }
}
