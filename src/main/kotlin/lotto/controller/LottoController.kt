package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoCalculator
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoPurchaseAmount
import lotto.domain.ManualLottoCount
import lotto.domain.WinningLotto
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
        val manualLottoNumber = getManualLottoNumbers(manualLottoCount.lottoCount)
        val lottos = getAndPrintPurchasedLottos(manualLottoNumber, purchaseAmount, manualLottoCount.lottoCount)
        val lottoCalculator = getWinningInfoAndCalculator()
        val winningStats = getAndPrintWinningStats(lottoCalculator, lottos)
        val prize = lottoCalculator.calculatePrize(winningStats)
        outputView.printProfit(lottoCalculator.calculateProfit(prize, purchaseAmount.money))
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

    private fun getManualLottoNumbers(purchaseCount: Int): List<Lotto> {
        return retryInput {
            val lottos = inputView.getManualLottoNumbers(purchaseCount)
            val manualLottos = lottos.map { Lotto(stringToLottoNumber(it)) }
            manualLottos
        }
    }

    private fun stringToLottoNumber(string: String): List<LottoNumber> {
        val splitString = string.split(",").map { it.trim() }
        return splitString.map { LottoNumber(it.toInt()) }
    }

    private fun getAndPrintPurchasedLottos(
        manualLotto: List<Lotto>,
        purchaseAmount: LottoPurchaseAmount,
        manualLottoCount: Int,
    ): List<Lotto> {
        val lottos = lottoMachine.buyLottos(purchaseAmount.purchasableCount - manualLottoCount)
        outputView.printPurchasedLottos(manualLotto + lottos, manualLottoCount, purchaseAmount.purchasableCount - manualLottoCount)
        return manualLotto + lottos
    }

    private fun getWinningInfoAndCalculator(): LottoCalculator {
        val winningNumber = getWinningNumber()
        val winningLotto = getWinningLotto(winningNumber)
        return LottoCalculator(winningLotto.winningNumber, winningLotto.bonusNumber)
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
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }

    companion object {
        private const val DELIMITERS = ","
    }
}
