package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoCalculator
import lotto.domain.LottoMachine
import lotto.domain.LottoMachine.Companion.LOTTO_PRICE
import lotto.domain.LottoNumber
import lotto.domain.LottoPurchaseAmount
import lotto.domain.ManualLottoCount
import lotto.domain.OrderSheet
import lotto.domain.WinningLotto
import lotto.generator.AutoLottoGenerator
import lotto.generator.ManualLottoGenerator
import lotto.util.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val lottoMachine = LottoMachine()

    fun run() {
        val orderSheet = getOrderSheet()
        val manualLottoNumber = getManualLottoNumbers(orderSheet.manualLottoCount.lottoCount)
        val lottos = getAndPrintPurchasedLottos(orderSheet, manualLottoNumber)
        val lottoCalculator = getWinningInfoAndCalculator()
        val winningStats = getAndPrintWinningStats(lottoCalculator, lottos)
        val prize = lottoCalculator.calculatePrize(winningStats)
        outputView.printProfit(lottoCalculator.calculateProfit(prize, orderSheet.purchaseAmount.money))
    }

    private fun getOrderSheet(): OrderSheet {
        val purchaseAmount = getPurchaseAmount()
        val manualLottoCount = getManualLottoCount(purchaseAmount.purchasableCount)

        return OrderSheet(purchaseAmount, manualLottoCount)
    }

    private fun getPurchaseAmount(): LottoPurchaseAmount {
        return retryInput {
            LottoPurchaseAmount(inputView.getPurchaseAmount().toInt(), LOTTO_PRICE)
        }
    }

    private fun getManualLottoCount(purchasableCount: Int): ManualLottoCount {
        return retryInput {
            ManualLottoCount(inputView.getManualLottoCount().toInt(), purchasableCount)
        }
    }

    private fun getManualLottoNumbers(purchaseCount: Int): List<Lotto> {
        return retryInput {
            val input = inputView.getManualLottoNumbers(purchaseCount)
            val lottos = input.map { it.split(DELIMITERS).map { it.trim().toInt() } }
            lottoMachine.buyLottos(ManualLottoGenerator(lottos))
        }
    }

    private fun getAndPrintPurchasedLottos(
        orderSheet: OrderSheet,
        manualLottos: List<Lotto>,
    ): List<Lotto> {
        val lottos = manualLottos + lottoMachine.buyLottos(AutoLottoGenerator(orderSheet.autoCount))
        outputView.printPurchasedLottos(lottos, orderSheet.manualLottoCount.lottoCount, orderSheet.autoCount)
        return lottos
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
            outputView.printWinningStatsByRank(state, count)
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
