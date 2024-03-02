package controller

import model.Count
import model.Lotteries
import model.Lottery
import model.LotteryGenerator
import model.LotteryNumber
import model.LotteryResultEvaluator
import model.Money
import model.Profit
import model.ProfitRate
import model.ProfitStatusDecider
import model.WinningResult
import view.InputView
import view.OutputView
import java.math.BigDecimal

class LotteryController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val lotteryResultEvaluator: LotteryResultEvaluator = LotteryResultEvaluator(),
    private val profit: Profit = Profit(),
) {
    private val lotteryGenerator = LotteryGenerator()

    fun start() {
        runCatching {
            val purchaseAmount = Money.from(inputView.readPurchaseAmount())
            val lotteries = lotterySellingService(purchaseAmount)

            val winningNumbers = Lottery.fromInput(inputView.readWinningNumbers())
            val bonusNumber = LotteryNumber.bonusNumber(winningNumbers, inputView.readBonusNumber())

            showResultService(lotteries, winningNumbers, bonusNumber, purchaseAmount)
        }.onFailure { exception -> println(exception.message) }
    }

    private fun lotterySellingService(purchaseAmount: Money): Lotteries {
        val manualCount = Count.from(inputView.readManualLotteryCount())
        outputView.showManualLottoInputGuide()
        val manualLotteries = Lotteries(List(manualCount.amount) { Lottery.fromInput(readln()) })

        val autoCount = manualCount.getAutoCount(purchaseAmount)
        val autoLotteries = getLotteries(autoCount)

        val lotteries = Lotteries.of(manualLotteries, autoLotteries)
        outputView.showPurchasedLotteries(lotteries, manualCount)

        return lotteries
    }

    private fun getLotteries(lotteryCount: Int): Lotteries = Lotteries(List(lotteryCount) { lotteryGenerator.generate() })

    private fun showResultService(
        lotteries: Lotteries,
        winningNumbers: Lottery,
        bonusNumber: LotteryNumber,
        purchaseAmount: Money,
    ) {
        val winningResult = getWinningResult(lotteries, winningNumbers, bonusNumber)
        outputView.showWinningResult(winningResult)

        val totalPrize = getTotalPrize(winningResult)
        showRateResult(purchaseAmount, totalPrize)
    }

    private fun getWinningResult(
        lotteries: Lotteries,
        winningNumbers: Lottery,
        bonusNumber: LotteryNumber,
    ): WinningResult = lotteryResultEvaluator.evaluate(lotteries, winningNumbers, bonusNumber)

    private fun getTotalPrize(winningResult: WinningResult): Money {
        var totalPrize = Money(BigDecimal(0))

        for (result in winningResult.result) {
            totalPrize += (result.key.winningPrize * result.value)
        }

        return totalPrize
    }

    private fun showRateResult(
        purchaseAmount: Money,
        totalPrize: Money,
    ) {
        val profitRate = getProfitRate(purchaseAmount, totalPrize)
        outputView.showProfitRate(profitRate, ProfitStatusDecider.decide(purchaseAmount, totalPrize))
    }

    private fun getProfitRate(
        purchaseAmount: Money,
        totalPrize: Money,
    ): ProfitRate = profit.calculateRate(purchaseAmount, totalPrize)
}
