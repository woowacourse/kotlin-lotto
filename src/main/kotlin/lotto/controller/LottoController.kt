package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoCalculator
import lotto.domain.LottoCount
import lotto.domain.LottoMachine
import lotto.domain.LottoNumber
import lotto.domain.LottoPurchaseAmount
import lotto.domain.OrderSheet
import lotto.domain.TotalPurchasableLottoCount
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
        val manualLottoNumber = getManualLottoNumbers(orderSheet.purchasableLottoCount.manualLottoCount.count)
        val lottos = getAndPrintPurchasedLottos(orderSheet, manualLottoNumber)
        val lottoCalculator = getWinningInfoAndCalculator()
        val winningStats = getAndPrintWinningStats(lottoCalculator, lottos)
        val prize = lottoCalculator.calculatePrize(winningStats)
        outputView.printProfit(lottoCalculator.calculateProfit(prize, orderSheet.purchaseAmount.money))
    }

    private fun getOrderSheet(): OrderSheet {
        val purchaseAmount = getPurchaseAmount()
        val totalPurchasableLottoCount = getTotalPurchasableLottoCount(purchaseAmount)

        return OrderSheet(purchaseAmount, totalPurchasableLottoCount)
    }

    private fun getPurchaseAmount(): LottoPurchaseAmount {
        return retryInput {
            LottoPurchaseAmount(inputView.getPurchaseAmount())
        }
    }

    private fun getTotalPurchasableLottoCount(purchaseAmount: LottoPurchaseAmount): TotalPurchasableLottoCount {
        return retryInput {
            val manualLottoCount = LottoCount(inputView.getManualLottoCount())
            val purchasableLottoCount = LottoCount(lottoMachine.getPurchasableLottoCount(purchaseAmount))
            TotalPurchasableLottoCount(manualLottoCount, purchasableLottoCount)
        }
    }

    private fun getManualLottoNumbers(purchaseCount: Int): List<Lotto> {
        return retryInput {
            val lottos = inputView.getManualLottoNumbers(purchaseCount)
            lottoMachine.buyLottos(ManualLottoGenerator(lottos))
        }
    }

    private fun getAndPrintPurchasedLottos(
        orderSheet: OrderSheet,
        manualLottos: List<Lotto>,
    ): List<Lotto> {
        val lottos = manualLottos + lottoMachine.buyLottos(AutoLottoGenerator(orderSheet.purchasableLottoCount.autoLottoCount.count))
        outputView.printPurchasedLottos(
            lottos,
            orderSheet.purchasableLottoCount.manualLottoCount.count,
            orderSheet.purchasableLottoCount.autoLottoCount.count,
        )
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
            val winningNumber = inputView.getWinningNumber()
            Lotto(winningNumber.map { LottoNumber.from(it) })
        }
    }

    private fun getWinningLotto(winningNumber: Lotto): WinningLotto {
        return retryInput {
            val bonusNumber = inputView.getBonusNumber()
            WinningLotto(winningNumber, LottoNumber.from(bonusNumber))
        }
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }
}
